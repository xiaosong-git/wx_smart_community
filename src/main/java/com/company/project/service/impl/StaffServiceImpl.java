package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.StaffMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Staff;
import com.company.project.model.User;
import com.company.project.service.StaffService;
import com.company.project.service.UserService;
import com.company.project.util.DESUtil;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/02/17.
 */
@Service
@Transactional
public class StaffServiceImpl extends AbstractService<Staff> implements StaffService {
    @Resource
    private StaffMapper hStaffMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private StaffMapper staffMapper;

    @Resource
    private UserService userService;
    private IService iService = new WxService();
    Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);
    @Resource
    private StaffService staffService;
    @Override
    public Result addStaff(String areaId, String name, String idNO, String phone) {
        User user = userMapper.findByNamePhone(name, phone);
        String idNow = DESUtil.encode("iB4drRzSrC", idNO);
        if (user==null){
            //新增员工
            user=new User();
            user.setIdNo(idNow);
            user.setPhone(phone);
            user.setName(name);
            user.setIsManager("10");//普通物管
            user.setExt1(areaId);
            userService.save(user);

        }else {
            user.setIsManager("10");
            user.setExt1(areaId);
            userService.update(user);
        }
        Long userId = user.getId();
       //普通物管
        Staff staff = staffMapper.findbyAreaId(areaId, userId);
        if (staff==null){
            staff=new Staff();
            staff.setAreaId(areaId);
            staff.setUserId(userId);
            int save = save(staff);
            if (save>0){
                System.out.println("添加员工成功");

                if (user.getWxOpenId() != null & !"".equals(user.getWxOpenId())) {
                    //todo 添加微信菜单
                    List<String> openIds = new LinkedList<>();
                    openIds.add(user.getWxOpenId());

                    try {
                       iService.batchMovingUserToNewTag(openIds, 101);
                    } catch (WxErrorException e) {
                        logger.error("发送微信物管菜单错误{}",user.getWxOpenId());
                    }
                }
                return ResultGenerator.genSuccessResult("添加员工成功");
            }
            return ResultGenerator.genFailResult("存入失败");
        }
        if ("delete".equals(staff.getStatus())) {
            staff.setStatus("");
            staff.setUserId(userId);
            staff.setAreaId(areaId);
            int update = staffService.update(staff);
            if (update>0){
                //todo 添加微信菜单
                List<String> openIds = new LinkedList<>();
                openIds.add(user.getWxOpenId());

                try {
                    iService.batchMovingUserToNewTag(openIds, 101);
                } catch (WxErrorException e) {
                    logger.error("发送微信物管菜单错误{}",user.getWxOpenId());
                }
                return ResultGenerator.genSuccessResult("添加员工成功");
            }

        }

        return ResultGenerator.genFailResult("存入用户失败！");
    }

    /**
     *修改未实名的用户姓名身份证号
     */
    public Result editStaff(Long userId,String name, String idNO, String phone) {
        User user = userService.findById(userId);
        if (user==null){
            return ResultGenerator.genFailResult("未查询到改用户");
        }
        if("T".equals(user.getIsAuth())){
            return ResultGenerator.genSuccessResult("用户已实名，无法修改");
        }
        user.setName(name);
        user.setPhone(phone);
        //如果有修改身份证号
        if (!"".equals(idNO)) {
            String idNow = DESUtil.encode("iB4drRzSrC", idNO);
            user.setIdNo(idNow);
        }
        int update = userService.update(user);
        if (update>0){
            return ResultGenerator.genSuccessResult("操作成功");
        }
        return ResultGenerator.genFailResult("操作失败！");
    }
    /**
     * 从staff表删除员工
     */
    public Result deleteStaff(String areaId,Long userId){
        User user = userService.findById(userId);
        if (user==null){
            return ResultGenerator.genFailResult("未查询到改用户");
        }
        Staff staff = staffMapper.findbyAreaId(areaId, userId);
        if (staff==null){
            return ResultGenerator.genFailResult("未查询到小区员工");
        }
        staff.setStatus("delete");
        int update = staffService.update(staff);
        if (update>0){
            return ResultGenerator.genSuccessResult("操作成功");
        }
        return ResultGenerator.genSuccessResult("操作失败");
    }
}
