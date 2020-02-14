package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Hourse;

public interface HourseMapper extends Mapper<Hourse> {
	List<Hourse> findHouse(@Param("name") String name,@Param("phone") String phone);
	List<Hourse> authFamily(@Param("name") String name,@Param("idCard") String idCard);
}