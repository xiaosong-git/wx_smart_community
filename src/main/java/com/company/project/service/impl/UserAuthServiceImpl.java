package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.UserAuthMapper;
import com.company.project.model.UserAuth;
import com.company.project.service.UserAuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class UserAuthServiceImpl extends AbstractService<UserAuth> implements UserAuthService {
    @Resource
    private UserAuthMapper tblUserAuthMapper;

}
