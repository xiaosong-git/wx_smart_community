package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.FamilyMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Family;
import com.company.project.model.User;
import com.company.project.service.FamilyService;
import com.company.project.core.AbstractService;
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
}
