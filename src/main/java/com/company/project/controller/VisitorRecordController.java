package com.company.project.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Area;
import com.company.project.model.User;
import com.company.project.model.VisitorRecord;
import com.company.project.service.UserService;
import com.company.project.service.VisitorRecordService;
import com.company.project.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/04.
*/
@RestController
@RequestMapping("/visitor/record")
public class VisitorRecordController {
    @Resource
    private VisitorRecordService visitorRecordService;

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

    @PostMapping("/visitRequest")
        public Result visitRequest(Long userId,Long areaId,String phone) {
        User user = userService.findById(userId);
        if(null == user){
            return ResultGenerator.genFailResult("系统错误");
        }
        List<User> visitors = visitorRecordService.findByAreaidPhone(areaId,phone);
        if(visitors.size() <= 0 ){
            return ResultGenerator.genFailResult("受访人信息错误，请核对后填写");
        }
        User visitor = visitors.get(0);
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
        record.setAreaId(String.valueOf(areaId));
        record.setRecordtype(1);
        visitorRecordService.save(record);
        return ResultGenerator.genSuccessResult("访问成功");
    }
}
