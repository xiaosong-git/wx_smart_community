package com.company.project.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.AccessRecord;
import com.company.project.model.User;
import com.company.project.model.VisitorRecord;
import com.company.project.service.AccessRecordService;
import com.company.project.service.ParamsService;
import com.company.project.service.UserService;
import com.company.project.service.VisitorRecordService;
import com.company.project.util.Base64;
import com.company.project.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/03/06.
*/
@RestController
@RequestMapping("/access/record")
public class AccessRecordController {
    @Resource
    private AccessRecordService accessRecordService;
    @Resource
    private ParamsService paramsService;
    @Resource
    private VisitorRecordService visitorRecordService;
    @Resource
    private UserService userService;
    @PostMapping("/add")
    public Result add(AccessRecord accessRecord) {
        accessRecordService.save(accessRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        accessRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(AccessRecord accessRecord) {
        accessRecordService.update(accessRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        AccessRecord accessRecord = accessRecordService.findById(id);
        return ResultGenerator.genSuccessResult(accessRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AccessRecord> list = accessRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/allRecord")
    public Result allRecord(String result) throws Exception {
        String recordId = new String(Base64.decode(result),"UTF-8");
        VisitorRecord visitorRecord = visitorRecordService.findById(Long.valueOf(recordId));
        String date = DateUtil.getCurDate();
        if(visitorRecord.getVisitDate().compareTo(date) < 0){
            return ResultGenerator.genFailResult("访客二维码失效");
        }else{
            Long userId = visitorRecord.getUserId();
            Long areaId = visitorRecord.getAreaId();
            User user = userService.findById(userId);
            System.out.println(user.getName());

            List<Map<String,Object>> list = accessRecordService.findUserAccessArea(userId,areaId);
            Map<String,Object> map = new HashMap<>();
            String img = user.getImgUrl();
            if (img!=null){
                System.out.println(img);
                String imageServerUrl = paramsService.findValueByName("imageServerUrl");
                System.out.println(imageServerUrl);
                map.put("imgUrl",imageServerUrl +img);
            }
            String name = user.getName();
            String phone = user.getPhone();
            map.put("name",name);
            map.put("phone",phone);
            if(list.size()>0){

                for(int i=0;i<list.size();i++){
                    list.get(i).remove("img_url");
                    list.get(i).remove("name");
                    list.get(i).remove("phone");
                }
                map.put("data",list);

            }
            return ResultGenerator.genSuccessResult(map);

        }

    }

    @PostMapping("/accessArea")
    public Result accessArea(String turnover,String result,Long managerId,Long areaId) throws Exception {
        System.out.println("areaId:"+areaId);
        String recordId = new String(Base64.decode(result),"UTF-8");
        VisitorRecord visitorRecord = visitorRecordService.findById(Long.valueOf(recordId));
        Long userId = visitorRecord.getUserId();
        AccessRecord accessRecord = new AccessRecord();
        String date =DateUtil.getCurDate();
        String time = DateUtil.getCurTime();
        accessRecord.setUserId(userId);
        accessRecord.setAreaId(areaId);
        accessRecord.setManagerId(String.valueOf(managerId));
        accessRecord.setPassDate(date);
        accessRecord.setPassTime(time);
        accessRecord.setPersontype("visitor");
        accessRecord.setTurnOver(turnover);
        int success = accessRecordService.save(accessRecord);
        if(success ==1){
            return ResultGenerator.genSuccessResult("通行成功");
        }else{
            return ResultGenerator.genFailResult("系统错误");
        }

    }
}
