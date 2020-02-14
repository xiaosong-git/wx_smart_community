package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.OrgMapper;
import com.company.project.model.Org;
import com.company.project.service.OrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class OrgServiceImpl extends AbstractService<Org> implements OrgService {
    @Resource
    private OrgMapper tOrgMapper;

}
