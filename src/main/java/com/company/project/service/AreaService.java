package com.company.project.service;
import com.company.project.model.Area;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface AreaService extends Service<Area> {
	List<Area> areaList(String areaCode);
}
