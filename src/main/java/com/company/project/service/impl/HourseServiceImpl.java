package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.HourseMapper;
import com.company.project.model.Hourse;
import com.company.project.service.HourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class HourseServiceImpl extends AbstractService<Hourse> implements HourseService {
    @Resource
    private HourseMapper hHourseMapper;

	@Override
	public List<Hourse> findHouse(String name, String phone) {
		// TODO Auto-generated method stub
		return hHourseMapper.findHouse(name, phone);
	}

	@Override
	public List<Hourse> authFamily(String name, String idCard) {
		// TODO Auto-generated method stub
		return hHourseMapper.authFamily(name, idCard);
	}

	
    
    

}
