package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.*;
import com.company.project.model.*;
import com.company.project.service.AreaService;
import com.company.project.service.ParamsService;
import com.company.project.service.UserService;
import com.company.project.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.result.WxError;
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
    private HourseMapper hourseMapper;
    @Resource
    private UserAuthMapper userAuthMapper;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private ParamsService paramService;
    @Resource
    private FamilyMapper familyMapper;
    private IService iService = new WxService();
    @Resource
    private StaffMapper StaffMapper;
    @Resource
    private AreaService areaService;
    @Override
    public Result verify(long userId, String idNO, String name, String idHandleImgUrl,String localImgUrl) {
        try {
//            if (isVerify(userId)) {
//                return ResultGenerator.genFailResult( "已经实名认证过","fail");
//            }
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
//                UserAuth userAuth = userAuthMapper.localPhoneResult(idNoMW, realName);
//                if (userAuth!=null){
//                    idHandleImgUrl=userAuth.getIdhandleimgurl();//目前存在无法两张人像比对的bug
//                    logger.info("本地实人认证成功上一张成功图片为：{}",userAuth.getIdhandleimgurl());
//                }else{
                    String photoResult = auth(idNO, realName, localImgUrl);
                    if (!"success".equals(photoResult)) {
                        return ResultGenerator.genFailResult(photoResult, "fail");
                    }
//                }
            }catch (Exception e){
                e.printStackTrace();
                return ResultGenerator.genFailResult( "图片上传出错!","fail");
            }
            Date date = new Date();
            String authDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            User verifyUser = bindManage(userId, idNoMW,name);
//            User verifyUser = new User();
            verifyUser.setAuthDate( authDate);
//            verifyUser.setId(userId);
            verifyUser.setImgUrl( idHandleImgUrl);
            verifyUser.setName(realName);
            verifyUser.setIsAuth("T");//F:未实名 T：实名 N:正在审核中 E：审核失败
            verifyUser.setIdNo(idNoMW);
            if(update( verifyUser) > 0){
                String key = userId + "_isAuth";
                //redis修改
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("isAuth", "T");
                resultMap.put("userId",verifyUser.getId());
                return ResultGenerator.genSuccessResult(resultMap);
            }
            return ResultGenerator.genFailResult( "实名认证失败","fail");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
            return ResultGenerator.genFailResult("异常，请稍后再试","fail");
        }
    }

    /**
     * 绑定管理员,或者户主
     */
    public User bindManage(Object userId,String idNoMW,String name) throws WxErrorException {
        User user = hUserMapper.getUserFromId(userId);
        String wxOpenId = user.getWxOpenId();
        //是否管理员
        User manage=hUserMapper.findByIdNo(idNoMW);
        if (manage==null){

            User user1 = bindHouseholder(userId, idNoMW, name);
            return user1;
        }
        //发送给微信贴上标签
        List<String> openIds=new LinkedList<>();
        openIds.add(wxOpenId);
        iService.batchMovingUserToNewTag(openIds,100);//100 物业超管标签号
        //修改绑定用户
        user.setWxOpenId("");
        manage.setWxOpenId(wxOpenId);
        update(user);
        update(manage);
        logger.info("绑定超管成功,{},{}",user.getId(),manage.getId());
        return manage;
    }

    /**
     * 绑定户主
     */
    public User bindHouseholder(Object userId,String idNoMW,String name) throws WxErrorException {
        User user = hUserMapper.getUserFromId(userId);
        String wxOpenId = user.getWxOpenId();
        //是否普通用户
        User Householder=hUserMapper.findByIdNoName(idNoMW,name);

        if (Householder==null){
            return user;
        }
        //查询是否
        User byStaff = hUserMapper.findByStaff(Householder.getId());
        if (byStaff==null){
            user.setWxOpenId("");
            Householder.setWxOpenId(wxOpenId);
            update(user);
            update(Householder);
            return Householder;
        }
        List<String> openIds=new LinkedList<>();
        openIds.add(wxOpenId);
        iService.batchMovingUserToNewTag(openIds,101);
        //修改绑定用户
//        if ()
        user.setWxOpenId("");
        byStaff.setWxOpenId(wxOpenId);
        update(user);
        update(byStaff);
        return byStaff;
    }
    @Override
    public Result uploadPhoto(String userId, String mediaId, String type) throws Exception {
//        String time = DateUtil.getSystemTimeFourteen();
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
        String fileName = newFile.getAbsolutePath();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //获取文件
        byte[] photo = FilesUtils.getPhoto(fileName);
        //压缩
        String newFileName = userId+System.currentTimeMillis() + "."+suffix;
        File compressImg = FilesUtils.getFileFromBytes(FilesUtils.compressUnderSize(photo, 10240L), url+File.separator, newFileName);
        String name = compressImg.getAbsolutePath();
        logger.info(name);
        OkHttpUtil okHttpUtil=new OkHttpUtil();
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("type",type);
        map.put("file",compressImg);
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
    public Map<String,Object> getUser(String openId){

        Map<String,Object> userInfo=new HashMap<>();

        User user = hUserMapper.getUserFromOpenId(openId);

        //房子信息
       Hourse hourse= hourseMapper.getHouseFromOpenId(openId);
//
//        if (user==null){
//             user=new User();
//            user.setCreateTime(DateUtil.getSystemTime());
//            user.setWxOpenId(openId);
//            int save = this.save(user);
//        }
        userInfo.put("user",user);
        //家庭信息
        Family family = familyMapper.getFamilyFromOpenId(openId);
        if (hourse!=null){
            System.out.println("---有房子---");
            userInfo.put("hourse","T");
        }else {
            userInfo.put("hourse","F");
        }
        if (family!=null){
            System.out.println("---有家庭---");
            userInfo.put("family","T");
        }else {
            userInfo.put("family","F");
        }

        return userInfo;
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
        String userName = DESUtil.encode(key,realName);
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

	@Override
	public List<User> findList(String name, String phone) {
		// TODO Auto-generated method stub

		return hUserMapper.findList(name, phone);
	}
    @Override
    public List<User> finUserList(String name, String idCard) {
        // TODO Auto-generated method stub
        String workKey = "iB4drRzSrC";//生产的des密码
        // update by cwf  2019/10/15 10:36 Reason:暂时修改为后端加密
        idCard = DESUtil.encode(workKey,idCard);
        List<User> list = hUserMapper.findUserList(name, idCard);
        return list;
    }
    //通过openId查找用户的实人信息
    @Override
    public Result userAuthInfo(String openId) {

        User userInfo = hUserMapper.getUserFromOpenId(openId);
        if (userInfo!=null){
            String workKey = "iB4drRzSrC";//生产的des密码
            // update by cwf  2019/10/15 10:36 Reason:暂时修改为后端加密
            if (userInfo.getIdNo()!=null){
                userInfo.setIdNo(DESUtil.decode(workKey,userInfo.getIdNo()));
            }
            return ResultGenerator.genSuccessResult(userInfo);
        }
        return ResultGenerator.genFailResult("未查询到实人信息");


    }

    //批量生成管理员个性化菜单
    @Override
    public Result creatUserToTag(int tagId) {
        //由于tag每次只能生成50个openId
        int count=0;
        int size=50;
        List<String> openIds=new LinkedList<>();

        List<User> list = null;
        if (tagId==100){

        list=hUserMapper.findManager();
        }else if (tagId==101){
            list=hUserMapper.findStaff();
        }
        WxError wxError;
        if (list!=null) {
            for (User user : list) {
                count++;
                openIds.add(user.getWxOpenId());

                if (count % size == 0) {
                    try {
                         wxError = iService.batchMovingUserToNewTag(openIds, 100);
                        logger.info(wxError.getErrmsg());
                    } catch (WxErrorException e) {
                        logger.error("批量生成管理员菜单报错", e);
                    }
                    openIds.removeAll(list);
                }
            }
        }

        try {
             wxError = iService.batchMovingUserToNewTag(openIds, 100);
            logger.info(wxError.getErrmsg());
        } catch (WxErrorException e) {
            logger.error("批量生成管理员菜单报错", e);
        }
        return ResultGenerator.genSuccessResult(list);
    }

    @Override
    public Result findStaff(String openId) {
        User user = hUserMapper.getUserOpenId(openId);
        if (user==null){
            logger.info("查询管理员登入信息失败");
            return ResultGenerator.genFailResult("查询管理员登入信息失败");
        }
        String ext1 = user.getExt1();
        if (ext1 == null || "".equals(ext1)) {
            logger.info("查询管理员小区信息失败");
            return ResultGenerator.genFailResult("查询管理员小区信息失败");
        }
        List<User> staffs = hUserMapper.selectStaffUserByArea(ext1);
        if (staffs!=null){
            for (User staff : staffs) {
                if (!"".equals(staff.getIdNo())&&staff.getIdNo()!=null){

                    staff.setIdNo(DESUtil.decode("iB4drRzSrC",staff.getIdNo()));

                }
            }
        }
        Map<String,Object> map=new HashMap<>();

        Area area = areaService.findBy("id", Long.valueOf(ext1));
        if (area!=null){
            map.put("areaName",area.getAreaName());
        }
            map.put("areaId",ext1);
        map.put("staff",staffs);
        logger.info("查询成功");
        return ResultGenerator.genSuccessResult(map);
    }




}
