package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.Family;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FamilyMapper extends Mapper<Family> {
    Family findFamily(Long hourseId);

    @Delete("delete from "+ TableList.FAMILY+" where house_id=#{hourseId} and user_id=#{userId}")
    void deleteHourseUser(Long hourseId, Long userId);

    List<Family> findByUser(@Param("name") String name, @Param("idCard") String idCard);
    @Select("select * from "+TableList.FAMILY+ " f left join "+TableList.USER+" u" +
             " on f.user_id=u.id where u.wx_open_id=#{openId} limit 1")
    Family getFamilyFromOpenId(String openId);
}