package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.BuildingMapper;
import com.company.project.model.Building;
import com.company.project.service.BuildingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class BuildingServiceImpl extends AbstractService<Building> implements BuildingService {
    @Resource
    private BuildingMapper hBuildingMapper;

	@Override
	public List<Building> findBuilding(String areaId) {
		// TODO Auto-generated method stub
		return hBuildingMapper.findBuilding(areaId);
	}

	@Override
	public Building findBuildingById(Long buildingId) {
		return findById(buildingId);
	}


}
