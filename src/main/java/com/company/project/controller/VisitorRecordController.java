package com.company.project.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.model.VisitorRecord;
import com.company.project.service.UserService;
import com.company.project.service.VisitorRecordService;
import com.company.project.util.Base64;
import com.company.project.util.DateUtil;
import com.company.project.util.HttpUtil;
import com.company.project.weixin.model.WxTemplateData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.exception.WxErrorException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.project.weixin.MenuKey.REPLYVISIT;
import static com.company.project.weixin.MenuKey.URL;

/**
* Created by CodeGenerator on 2020/03/04.
*/
@RestController
@RequestMapping("/visitor/record")
public class VisitorRecordController {
    @Resource
    private VisitorRecordService visitorRecordService;

    private IService iService = new WxService();

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(VisitorRecord visitorRecord) {
        visitorRecordService.save(visitorRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        visitorRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(VisitorRecord visitorRecord) {
        visitorRecordService.update(visitorRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        VisitorRecord visitorRecord = visitorRecordService.findById(id);
        return ResultGenerator.genSuccessResult(visitorRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<VisitorRecord> list = visitorRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     *  访问申请
     *
     * @param userId    发起者ID
     * @param areaId    小区ID
     * @param phone     对方手机号
     * @return
     */
    @PostMapping("/visitRequest")
        public Result visitRequest(Long userId,Long areaId,String phone) throws WxErrorException {
        System.out.println("userId:"+userId);
        System.out.println("areaId:"+areaId);
        System.out.println("phone:"+phone);
        User user = userService.findById(userId);
        if(null == user){
            return ResultGenerator.genFailResult("系统错误");
        }
        List<User> visitors = visitorRecordService.findByAreaidPhone(areaId,phone);
        if(visitors.size() <= 0 ){
            return ResultGenerator.genFailResult("受访人信息错误，请核对后填写");
        }
        User visitor = visitors.get(0);
        if(visitor.getId()==user.getId()){
            return ResultGenerator.genFailResult("请不要对自己发起访问");
        }
        String visitDate = DateUtil.getCurDate();
        String visitTime = DateUtil.getCurTime();
        VisitorRecord record = new VisitorRecord();
        record.setUserId(user.getId());
        record.setVisitDate(visitDate);
        record.setVisitTime(visitTime);
        record.setVisitorId(visitor.getId());
        record.setCstatus("applying");
        record.setEndDate(visitDate);
        record.setStartDate(visitDate);
        record.setAreaId(areaId);
        record.setRecordtype(1);
        visitorRecordService.save(record);
        Long recordId = visitorRecordService.findRecordId(user.getId(),visitor.getId(),visitDate,visitTime);

        //消息推送模板
        TemplateSender sender = new TemplateSender();
        sender.setTemplate_id("RKBL9LJSvvOqP8HH8uMzuMH41m_kOcVD0AVtlmsDV1A");
        sender.setTouser(visitor.getWxOpenId());
        Map<String, WxTemplateData> dataMap = new HashMap<>();
        dataMap.put("first",new WxTemplateData("访问申请", "#173177"));
        dataMap.put("keyword1",new WxTemplateData(user.getName(), "#173177"));
        dataMap.put("keyword2",new WxTemplateData(user.getPhone(), "#173177"));
        dataMap.put("keyword3",new WxTemplateData(visitDate, "#173177"));
        String params = "?recordId="+recordId;
        sender.setUrl(URL+REPLYVISIT+params);
        sender.setData(dataMap);
        TemplateSenderResult result = iService.templateSend(sender);
        System.out.println(result);
        return ResultGenerator.genSuccessResult("访问成功");
    }

    /**
     *  被访者的需要审核的记录
     *
     * @param userId
     * @return
     */
    @PostMapping("/records")
    public Result records(Long userId) {

       List<Map<String,Object>>  list = visitorRecordService.findApplying(userId);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/replyRecord")
    public Result replyRecord(Long recordId,String cstatus) {
        int result = visitorRecordService.updateCstatus(recordId,cstatus);
        if(result == 1){
            return ResultGenerator.genSuccessResult("审核成功");
        }
        return ResultGenerator.genFailResult("系统异常，请重试");
    }

    @PostMapping("/codeIndex")
    public Result codeIndex(Long userId) {
        List<Map<String,Object>>  list = userService.findVisitSuccess(userId);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/createVisitCode")
    public Result createVisitCode(String recordId){
        try {
            String encode = Base64.encode(recordId.getBytes("UTF-8"));
            return ResultGenerator.genSuccessResult(encode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常","");
    }
    @PostMapping("/getUserByRecordId")
    public Result getUserByRecordId(Long recordId) {
        List<Map<String,Object>> list = visitorRecordService.findUserByRecordId(recordId);
        if(list.size()>0){
            String visitDate = String.valueOf(list.get(0).get("visit_date"));
            String now = DateUtil.getCurDate();
            System.out.println(visitDate.compareTo(now));
            Map<String,Object> map = list.get(0);
            if(visitDate.compareTo(now)<0){
                map.put("expired","T");
                return ResultGenerator.genSuccessResult(map);
            }else{
                map.put("expired","F");
                return ResultGenerator.genSuccessResult(map);
            }
        }else{
            return ResultGenerator.genFailResult("无数据");
        }

    }

    @PostMapping("/test")
    public Result test(Long recordId) throws Exception {
        List<Map<String,Object>> list = visitorRecordService.findUserByRecordId(recordId);
        return ResultGenerator.genSuccessResult(list);
    }

}
