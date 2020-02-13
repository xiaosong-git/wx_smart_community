package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.UserAuthMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.model.UserAuth;
import com.company.project.service.ParamsService;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;
import com.company.project.util.*;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.exception.WxErrorException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper hUserMapper;
    @Resource
    private UserAuthMapper userAuthMapper;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private ParamsService paramService;
    private IService iService = new WxService();
    @Override
    public Result verify(Long userId, String idNO, String name, String idHandleImgUrl,String localImgUrl) {
        try {
            if (isVerify(userId)) {
                return ResultGenerator.genFailResult( "已经实名认证过","fail");
            }
            String realName = URLDecoder.decode(name, "UTF-8");
            if(idNO == null){
                return ResultGenerator.genFailResult( "身份证不能为空!","fail");
            }
            String workKey = "iB4drRzSrC";//生产的des密码
            // update by cwf  2019/10/15 10:36 Reason:暂时修改为后端加密
            String idNoMW = DESUtil.encode(workKey,idNO);
            if(realName == null){
                return ResultGenerator.genFailResult( "真实姓名不能为空!","fail");
            }
            //非空判断
            if(idHandleImgUrl == null){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
                return ResultGenerator.genFailResult( "图片上传失败，请稍后再试!","fail");
            }
            idHandleImgUrl = URLDecoder.decode(idHandleImgUrl, "UTF-8");
            try{
                // update by cwf  2019/10/15 10:54 Reason:改为加密后进行数据判断 原 idNO 现idNoMw
                //本地实人认证
                UserAuth userAuth = userAuthMapper.localPhoneResult(idNoMW, realName);
                if (userAuth!=null){
                    idHandleImgUrl=userAuth.getIdhandleimgurl();//目前存在无法两张人像比对的bug
                    logger.info("本地实人认证成功上一张成功图片为：{}",userAuth.getIdhandleimgurl());
                }else{
                    String photoResult = auth(idNO, realName, localImgUrl);
                    if (!"success".equals(photoResult)) {
                        return ResultGenerator.genFailResult(photoResult, "fail");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                return ResultGenerator.genFailResult( "图片上传出错!","fail");
            }
            Date date = new Date();
            String authDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            User verifyUser = new User();
            verifyUser.setAuthDate( authDate);
            verifyUser.setId(userId);
            verifyUser.setImgUrl( idHandleImgUrl);
            verifyUser.setName(realName);
            verifyUser.setIsAuth("T");//F:未实名 T：实名 N:正在审核中 E：审核失败
            verifyUser.setIdNo(idNoMW);
            if(update( verifyUser) > 0){
                String key = userId + "_isAuth";
                //redis修改
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("isAuth", "T");
                return ResultGenerator.genSuccessResult(resultMap);
            }
            return ResultGenerator.genFailResult( "实名认证失败","fail");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
            return ResultGenerator.genFailResult("异常，请稍后再试","fail");
        }
    }

    @Override
    public Result uploadPhoto(String userId, String mediaId, String type) {
        String time = DateUtil.getSystemTimeFourteen();
        //临时图片地址
        String url="D:\\test\\community\\tempotos";
//        String url="/project/weixin/community/tempotos";
        File file=new File(url);
        File newFile = null;
        try {
            newFile = iService.downloadTempMedia(mediaId, file);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String fileName = newFile.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = url+File.separator+userId+System.currentTimeMillis() + "."+suffix;
        File newNameFile=new File(newFileName);
        boolean b = newFile.renameTo(newNameFile);

        String name = newNameFile.getAbsolutePath();
        OkHttpUtil okHttpUtil=new OkHttpUtil();
        Map<String,Object> map=new HashMap();
        map.put("userId",userId);
        map.put("type",type);
        map.put("file",newNameFile);
        String imageServerApiUrl = paramService.findValueByName("imageServerApiUrl");
        String s = okHttpUtil.postFile(imageServerApiUrl, map, "multipart/form-data");//上传图片
        JSONObject jsonObject=JSONObject.parseObject(s);
        Map resultMap = JSON.parseObject(jsonObject.toString());
        if (resultMap.isEmpty()){
            return ResultGenerator.genFailResult("检测服务异常");
        }
        System.out.println(jsonObject.toString());
        Map verify=JSON.parseObject(resultMap.get("verify").toString());
        //人脸验证失败，返回值
        if ("fail".equals(verify.get("sign"))){
            return ResultGenerator.genFailResult(verify.get("desc").toString());
        }
        Map data=JSON.parseObject(resultMap.get("data").toString());
        data.put("img",name);
        //返回图片在服务器的地址
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 通过openid获取用户信息
     * @param openId
     * @return
     */
    @Override
    public User getUser(String openId){

        User user = hUserMapper.getUserFromOpenId(openId);
        if (user==null){
            user=new User();
            user.setCreateTime(DateUtil.getSystemTime());
            user.setWxOpenId(openId);
            int save = this.save(user);
        }
        return user;
    }

    @Override
        public Result userHourseInfo(Long userId) {
        List<Map<String,Object>> list = hUserMapper.userHourseInfo(userId);
        return ResultGenerator.genSuccessResult(list);
    }

    public  String auth(String idNO,String realName,String idHandleImgUrl) throws Exception {
        String string= String.valueOf(System.currentTimeMillis())+new Random().nextInt(10);
        JSONObject itemJSONObj =new JSONObject();
        itemJSONObj.put("custid", "1000000007");//账号
        itemJSONObj.put("txcode", "tx00010");//交易码
        itemJSONObj.put("productcode", "000010");//业务编码
        itemJSONObj.put("serialno", string);//流水号
        itemJSONObj.put("mac", createSign(string));//随机状态码   --验证签名  商户号+订单号+时间+产品编码+秘钥
        String key="2B207D1341706A7R4160724854065152";
        String userName =DESUtil.encode(key,realName);
        String certNo = DESUtil.encode(key,idNO);
        itemJSONObj.put("userName", userName);
//        itemJSONObj.put("certNo", "350424199009031238");
        itemJSONObj.put("certNo", certNo);
//        String photo= Base64.encode(FilesUtils.getImageFromNetByUrl(idHandleImgUrl));
        itemJSONObj.put("imgData", Configuration.GetImageStrFromPath(idHandleImgUrl,30));
        HttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost("http://t.pyblkj.cn:8082/wisdom/entrance/pub");
        StringEntity entityStr= new StringEntity(JSON.toJSONString(itemJSONObj), HTTP.UTF_8);
        entityStr.setContentType("application/json");
        postMethod.setEntity(entityStr);
        HttpResponse resp = httpClient.execute(postMethod);
        int statusCode = resp.getStatusLine().getStatusCode();
        ThirdResponseObj responseObj = new ThirdResponseObj();
        if (200 == statusCode) {

            String str = EntityUtils.toString(resp.getEntity(), HTTP.UTF_8);
            JSONObject jsonObject = JSONObject.parseObject(str);
            Map resultMap = JSON.parseObject(jsonObject.toString());
            if ("0".equals(resultMap.get("succ_flag").toString())){
                return "success";
            }else{
                return "身份信息不匹配";
            }
        }else{
            return "系统错误";
        }
    }
    public static String createSign(String str) throws Exception {
        StringBuilder sb=new StringBuilder();
        sb.append("1000000007000010").append(str).append("9A0723248F21943R4208534528919630");
        String newSign = MD5Util.MD5Encode(sb.toString(),"UTF-8");
        return newSign;
    }
    private boolean isVerify(Long userId) {
        User userFromOpenId = hUserMapper.getUserFromId(userId);
        if (userFromOpenId==null||userFromOpenId.getIsAuth()==null||"F".equals(userFromOpenId.getIsAuth())){
            return  false;
        }
        return true;
    }
}
