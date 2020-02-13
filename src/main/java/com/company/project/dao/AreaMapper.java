package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Area;

public interface AreaMapper extends Mapper<Area> {
	List<Area> areaList(@Param("areaCode") String areaCode);
}