package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Building;

public interface BuildingMapper extends Mapper<Building> {
	List<Building> findBuilding(@Param("areaId") String areaId);
}