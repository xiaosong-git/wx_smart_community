package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.Staff;
import org.apache.ibatis.annotations.Select;

public interface StaffMapper extends Mapper<Staff> {

    @Select("select * from "+ TableList.STAFF+" where area_id=#{areaId} and user_id= #{userId} limit 1")
    Staff findbyAreaId(String areaId,Long userId);
}