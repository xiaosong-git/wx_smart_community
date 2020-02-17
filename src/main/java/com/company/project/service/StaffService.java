package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Staff;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/02/17.
 */
public interface StaffService extends Service<Staff> {

    Result addStaff(String openId, String areaId);
}
