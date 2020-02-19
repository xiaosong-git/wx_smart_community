package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.FamilyMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Family;
import com.company.project.model.User;
import com.company.project.service.FamilyService;
import com.company.project.service.UserService;
import com.company.project.util.DESUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class FamilyServiceImpl extends AbstractService<Family> implements FamilyService {
    @Resource
    private FamilyMapper hFamilyMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Override
    public Result findFamilyUser(Long hourseId) {
        List<User> familyUser = userMapper.findFamilyUser(hourseId);
        for (User user : familyUser) {
            if ("".equals(user.getIdNo())||user.getIdNo()!=null){
               try {
                   user.setIdNo( DESUtil.decode("iB4drRzSrC",user.getIdNo()));
               }catch (Exception e){
                   continue;
               }
            }
        }
        return ResultGenerator.genSuccessResult(familyUser);
    }

    @Override
    public Result addFamilyNameIdNo(Long hourseId, String userName, String idNo, Long userId,String phone) {
        //查找用户是否存在idNo加密
        String workKey = "iB4drRzSrC";//生产的des密码
        // update by cwf  2019/10/15 10:36 Reason:暂时修改为后端加密
        String idNoMW = DESUtil.encode(workKey,idNo);
        User user = userMapper.findByPhoneName(phone, userName);
        //todo 可能得更改
        if (user==null){
            user=new User();
            user.setName(userName);
            user.setIdNo(idNoMW);
            user.setPhone(phone);
            userService.save(user);
        }else{
            Family familyUser = hFamilyMapper.findFamilyUser(hourseId, user.getId());
            if (familyUser!=null){
                return ResultGenerator.genFailResult("该用户已加入家庭，请勿重复添加！");
            }
        }
        Family family=new Family();
        family.setHouseId(hourseId);
        family.setUserId(user.getId());
        int save = save(family);
        if (save>0){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("操作失败");
    }

    @Override
    public Result editFamilyUser(String userName, String idNo, Long userId,String phone) {
        //查找用户是否存在idNo加密
        String workKey = "iB4drRzSrC";//生产的des密码
        String idNoMW = DESUtil.encode(workKey,idNo);
        User user=new User();
        user.setName(userName);
        user.setIdNo(idNoMW);
        user.setPhone(phone);
        user.setId(userId);
        int update = userService.update(user);
        if (update>0){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("操作失败");
    }

    @Override
    public Result deleteFamilyUser(Long hourseId, Long userId) {

        hFamilyMapper.deleteHourseUser(hourseId,userId);
        return ResultGenerator.genSuccessResult();
    }
    @Override
    public List<Family> findByUser(String name, String idCard) {
        // TODO Auto-generated method stub
        return hFamilyMapper.findByUser(name, idCard);
    }
}
