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

    /**
     * 新增员工
     * @param areaId 小区id
     * @param name  用户名
     * @param idNO  用户身份证
     * @param phone 电话号码
     * @return 是否成功
     */
    @PostMapping("/add")
    public Result list( @RequestParam String areaId,@RequestParam String name,
                         @RequestParam String idNO, String phone) {
        try {
            return  staffService.addStaff(areaId,name,idNO,phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }

    /**
     *
     * @param userId 用户id
     * @param name 姓名
     * @param idNO 身份证
     * @param phone 电话
     * @return
     */
    @PostMapping("/editStaff")
    public Result editStaff( @RequestParam Long userId, @RequestParam String name,
                        @RequestParam(defaultValue = "") String idNO, String phone) {
        try {
            return  staffService.editStaff(userId, name, idNO,  phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }
    @PostMapping("/deleteStaff")
    public Result delete( @RequestParam String areaId,@RequestParam Long userId) {
        try {
            return  staffService.deleteStaff(areaId,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");

    }
}
