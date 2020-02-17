package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Staff;
import com.company.project.service.StaffService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/02/17.
*/
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Resource
    private StaffService staffService;


    @PostMapping("/add")
    public Result list( @RequestParam String openId,@RequestParam String areaId) {

        return  staffService.addStaff(openId,areaId);

    }
}
