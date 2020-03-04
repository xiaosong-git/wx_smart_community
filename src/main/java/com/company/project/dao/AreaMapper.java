package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.Area;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AreaMapper extends Mapper<Area> {
	List<Area> areaList(@Param("areaCode") String areaCode);
	List<Area> findAreaById(@Param("userId") Long userId);
	List<Area> findByAreaId(@Param("userId") Long userId, @Param("areaId") Long areaId);
	List<Area> record(@Param("areaId") Long areaId);

	List<Map<String,Object>>  areaTimes(Long userId);
	Map<String,Object>  areaTime(Object userId,Long areaId);

	@Select("select * from "+ TableList.AREA +" a order by area_name")
	List<Area> findAllArea();
}