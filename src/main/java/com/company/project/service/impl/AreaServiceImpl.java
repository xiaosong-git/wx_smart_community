package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.AreaMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Area;
import com.company.project.model.User;
import com.company.project.service.AreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class AreaServiceImpl extends AbstractService<Area> implements AreaService {
    @Resource
    private AreaMapper hAreaMapper;

	@Override
	public List<Area> areaList(String areaCode) {
		// TODO Auto-generated method stub
		return hAreaMapper.areaList(areaCode);
	}

	@Override
	public List<Area> findAreaById(Long userId) {
		// TODO Auto-generated method stub
		return hAreaMapper.findAreaById(userId);
	}

	@Override
	public List<Area> findByAreaId(Long userId, Long areaId) {
		// TODO Auto-generated method stub
		return hAreaMapper.findByAreaId(userId, areaId);
	}

	@Override
	public List<Area> reports(Long areaId, String userName){
		// TODO Auto-generated method stub
		List<Area> list = hAreaMapper.record(areaId);
		return list;
	}


}
