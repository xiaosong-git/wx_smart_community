package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Area;
import org.apache.ibatis.annotations.Select;

public interface AreaMapper extends Mapper<Area> {
	List<Area> areaList(@Param("areaCode") String areaCode);
	List<Area> findAreaById(@Param("userId") Long userId);
	List<Area> findByAreaId(@Param("userId") Long userId,@Param("areaId") Long areaId);

}