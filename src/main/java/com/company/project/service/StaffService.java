package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Staff;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/02/17.
 */
public interface StaffService extends Service<Staff> {

    //添加员工
    Result addStaff(String areaId, String name, String idNO, String phone);
    //修改员工
    Result editStaff(Long userId,String name, String idNO, String phone);

    Result deleteStaff(String areaId, Long userId);
}
