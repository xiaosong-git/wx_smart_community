package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/02/11.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 实名认证
     *
     * @param openId         用户微信号id
     * @param idNO           身份证
     * @param realName       真实姓名
     * @param idHandleImgUrl 远程图片地址
     * @param localImgUrl    本地图片
     * @return
     */
    @RequestMapping("/verify")
    @ResponseBody
    public Result verify(@RequestParam String openId, @RequestParam String idNO,
                         @RequestParam String realName, @RequestParam String idHandleImgUrl, @RequestParam String localImgUrl
                        ,@RequestParam String phone  ) {
        try {
            return userService.verify(openId, idNO, realName, idHandleImgUrl, localImgUrl,phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");
    }

    /**
     * 实人认证图片上传
     *
     * @param openId  用户id
     * @param mediaId 微信临时图片
     * @param type    状态
     * @return
     */
    @RequestMapping("/uploadVerify")
    @ResponseBody
    public Result uploadPhoto(@RequestParam String openId, @RequestParam() String mediaId, @RequestParam() String type) {

        try {
            System.out.println(openId);
            System.out.println(mediaId);
            System.out.println(type);
            return userService.uploadPhoto(openId, mediaId, type);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResultGenerator.genFailResult("系统异常", "");
    }

    @RequestMapping("/userHourseInfo")
    @ResponseBody
    public Result userHourseInfo(@RequestParam Long userId) {
        try {
            return userService.userHourseInfo(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");
    }

    /**
     * 通过openId查找用户的实人信息
     * @param openId 微信号
     * @return
     */
    @RequestMapping("/userAuthInfo")
    @ResponseBody
    public Result userAuthInfo(@RequestParam String openId) {
        try {
            return userService.userAuthInfo(openId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");
    }

    /**
     * 根据 openId 生成个性化菜单
     *
     */
    @RequestMapping("/creatUserToTag")
    @ResponseBody
    public Result creatUserToTag(@RequestParam int tagId) {
        try {
            return userService.creatUserToTag(tagId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");
    }

    /**
     * 查询管理员所管理的员工
     */
    @RequestMapping("/findStaff")
    @ResponseBody
    public Result findStaff(@RequestParam String openId) {
        try {
            return userService.findStaff(openId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");
    }
    @RequestMapping("/findUserArea")
    @ResponseBody
    public Result findUserArea(@RequestParam String openId) {
        try {
            return userService.findUserArea(openId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常", "");
    }
}