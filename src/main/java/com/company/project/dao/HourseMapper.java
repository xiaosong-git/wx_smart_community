package com.company.project.dao;

import java.util.List;

import com.company.project.compose.TableList;
import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Hourse;
import org.apache.ibatis.annotations.Select;

public interface HourseMapper extends Mapper<Hourse> {
	List<Hourse> findHouse(@Param("name") String name,@Param("phone") String phone);
	List<Hourse> authFamily(@Param("name") String name,@Param("idCard") String idCard);
	List<Hourse> findHouse(@Param("name") String name,@Param("phone") String phone,@Param("idCard") String idCard);
	@Select("select * from "+ TableList.HOURSE+" h left join h_user u on h.rent_id=u.id \n" +
            "where u.wx_open_id=#{openId}")
    Hourse getHouseFromOpenId(String openId);
}