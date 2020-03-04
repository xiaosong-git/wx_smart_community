package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.model.Area;

import java.io.FileNotFoundException;
import java.util.List;

import com.company.project.model.User;
import org.apache.ibatis.annotations.Param;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface AreaService extends Service<Area> {
	List<Area> areaList(String areaCode);
	List<Area> findAreaById(Long userId);
	List<Area> findByAreaId(Long userId, Long areaId);
	List<Area> reports(Long areaId, String userName);
	List<Area> findAllArea();
}
