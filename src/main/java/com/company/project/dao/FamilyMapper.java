package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.Family;
import org.apache.ibatis.annotations.Delete;

public interface FamilyMapper extends Mapper<Family> {
    Family findFamily(Long hourseId);

    @Delete("delete from "+ TableList.FAMILY+" where house_id=#{hourseId} and user_id=#{userId}")
    void deleteHourseUser(Long hourseId, Long userId);
}