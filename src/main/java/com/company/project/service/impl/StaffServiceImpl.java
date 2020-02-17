package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.dao.StaffMapper;
import com.company.project.model.Staff;
import com.company.project.service.StaffService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/17.
 */
@Service
@Transactional
public class StaffServiceImpl extends AbstractService<Staff> implements StaffService {
    @Resource
    private StaffMapper hStaffMapper;

    @Override
    public Result addStaff(String openId, String areaId) {

        return null;
    }
}
