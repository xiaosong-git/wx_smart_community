package com.company.project.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.UserMapper;
import com.company.project.model.Family;
import com.company.project.model.Hourse;
import com.company.project.model.User;
import com.company.project.service.FamilyService;
import com.company.project.service.HourseService;
import com.company.project.service.UserService;
import com.company.project.util.DESUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/02/11.
*/
@RestController
@RequestMapping("/hourse")
public class HourseController {
    @Resource
    private HourseService hourseService;
    @Resource
    private UserService userservice;
    @Resource
    private FamilyService familyservice;
    @Resource
    private UserMapper userMapper;
    @PostMapping("/add")
    public Result add(Hourse hourse) {
        hourseService.save(hourse);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        hourseService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Hourse hourse) {
        hourseService.update(hourse);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Hourse hourse = hourseService.findById(id);
        return ResultGenerator.genSuccessResult(hourse);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Hourse> list = hourseService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 认证户主
     * @param houseaddr buidingid
     * @param paltaddr num
     * @param openId 微信号
     * @param name 姓名
     * @param idCard 身份证
     * @param phone 电话
     * @return
     */
    @PostMapping("/identityHouse")
    public Result identityHouse( @RequestParam() Long houseaddr, @RequestParam() String paltaddr,@RequestParam() String openId,
                                 @RequestParam() String name, @RequestParam() String idCard, @RequestParam() String phone) {
        Map<String, String> map = new HashMap<String, String>();
        List<Hourse> list = hourseService.findHouse(name, phone);
        User user = userMapper.findByPhoneName( phone,name);
        if (user==null){
            return ResultGenerator.genFailResult("未找到户主信息，请正确填写户主信息，或找物业确认！");
        }
        boolean flag = false;
        String isAuth = "F";
        if(list!=null) {
            for(Hourse h:list) {
                //楼栋的id以及楼栋
                if(h.getBuildingId().equals(houseaddr) &&h.getNum().equals(paltaddr)) {
                    Hourse hourse = new Hourse();
                    hourse.setIsAuth("T");
                    hourse.setId(h.getId());
                    hourseService.update(hourse);
                    isAuth = "T";
                    flag = true;
                    Family family = new Family();
                    family.setHouseId(h.getId());
                    if(h.getIsRent().equals("F")) {
                         family.setUserId(Long.parseLong(h.getUserId()));
                    }else {
                    	 family.setUserId(Long.parseLong(h.getRentId()));
                    }
                   
                    family.setIsJoin("T");
                    family.setStatus("0");
                    int save = familyservice.save(family);

                    System.out.println("是否生成家庭成功？"+save);
                }
            }
        }
        if(flag) {
            String workKey = "iB4drRzSrC";//生产的des密码
            // update by cwf  2019/10/15 10:36 Reason:暂时修改为后端加密
            idCard = DESUtil.encode(workKey,idCard);
            user.setIdNo(idCard);
            user.setWxOpenId(openId);
            userservice.update(user);
            map.put("isAuth", isAuth);
            map.put("userId", user.getId().toString());
            return ResultGenerator.genSuccessResult(map);
        }
        return ResultGenerator.genFailResult("认证失败，请让物业先添加用户");
    }
    @PostMapping("/authJoinFamily")
    public Result authJoinFamily( @RequestParam() Long houseaddr, @RequestParam() String paltaddr,@RequestParam() String openId,
                                  @RequestParam() String name, @RequestParam() String idCard) {
        List<User> userList = userservice.finUserList(name, idCard);
        String workKey = "iB4drRzSrC";//生产的des密码
         // update by cwf  2019/10/15 10:36 Reason:暂时修改为后端加密
         idCard = DESUtil.encode(workKey,idCard);
        List<Hourse> list = hourseService.authFamily(name, idCard);

        Map<String, String> map = new HashMap<String, String>();
        boolean flag = false;
        String isJoin = "F";
        if(list!=null) {
            for(Hourse h:list) {
                if(h.getBuildingId().equals(houseaddr) &&h.getNum().equals(paltaddr)) {
                    Family family = new Family();
                    family.setIsJoin("T");
                    family.setId(h.getFamily().getId());
                    familyservice.update(family);
                    isJoin = "T";
                    flag = true;
                }
            }
        }
        if(flag ) {
            User user = new User();
            user.setWxOpenId(openId);
            user.setId(userList.get(0).getId());
            userservice.update(user);
            map.put("isJoin", isJoin);
            map.put("userId", userList.get(0).getId().toString());
            return ResultGenerator.genSuccessResult(map);
        }

        return ResultGenerator.genFailResult("认证失败");
    }
}
