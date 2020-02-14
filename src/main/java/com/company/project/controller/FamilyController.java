package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.service.FamilyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2020/02/11.
*/
@RestController
@RequestMapping("/family")
public class FamilyController {
    @Resource
    private FamilyService familyService;
    @PostMapping("/findFamilyUser")
    public Result findFamilyUser(@RequestParam Long hourseId ) {
        try {
            return  familyService.findFamilyUser(hourseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }

    @PostMapping("/addFamilyNameIdNo")
    public Result addFamilyNameIdNo(@RequestParam Long hourseId, @RequestParam String userName, @RequestParam String idNo, Long userId ) {
        try {
            return  familyService.addFamilyNameIdNo(hourseId,userName,idNo,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }

}
