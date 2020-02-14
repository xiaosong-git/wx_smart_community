package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.model.Building;

import java.util.List;

/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface BuildingService extends Service<Building> {
	List<Building> findBuilding(String areaCode);
}
