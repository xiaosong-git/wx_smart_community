package com.company.project.service.impl;

import com.company.project.dao.FamilyMapper;
import com.company.project.model.Family;
import com.company.project.service.FamilyService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class FamilyServiceImpl extends AbstractService<Family> implements FamilyService {
    @Resource
    private FamilyMapper hFamilyMapper;

}
