package com.company.project.service.impl;

import com.company.project.dao.HourseMapper;
import com.company.project.model.Hourse;
import com.company.project.service.HourseService;
import com.company.project.util.DESUtil;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class HourseServiceImpl extends AbstractService<Hourse> implements HourseService {
    @Resource
    private HourseMapper hHourseMapper;

	@Override
	public List<Hourse> findHouse(String name, String phone, String idCard) {
		// TODO Auto-generated method stub
		return hHourseMapper.findHouse(name, phone);
	}

    
    

}
