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

    /**
     * 根据房子找到家庭用户
     * @param hourseId 房子id
     * @return List<user> 用户列表
     */
    @PostMapping("/findFamilyUser")
    public Result findFamilyUser(@RequestParam Long hourseId ) {
        try {
            return  familyService.findFamilyUser(hourseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }

    /**
     * 根据房间名字身份证号用户id添加家庭用户
     * @param hourseId
     * @param userName
     * @param idNo
     * @param userId
     * @return
     */
    @PostMapping("/addFamilyNameIdNo")
    public Result addFamilyNameIdNo(@RequestParam Long hourseId, @RequestParam String userName, @RequestParam String idNo, Long userId ) {
        try {
            return  familyService.addFamilyNameIdNo(hourseId,userName,idNo,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }
    @PostMapping("/editFamilyUser")
    public Result editFamilyUser(@RequestParam String userName,@RequestParam String idNo,Long userId ) {
        try {
            return  familyService.editFamilyUser(userName,idNo,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }
    @PostMapping("/deleteFamilyUser")
    public Result deleteFamilyUser(@RequestParam Long hourseId,@RequestParam Long userId ) {
        try {
            return  familyService.deleteFamilyUser(hourseId,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }

}
