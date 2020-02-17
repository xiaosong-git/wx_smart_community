package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Building;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuildingMapper extends Mapper<Building> {
	List<Building> findBuilding(@Param("areaId") String areaId);


}