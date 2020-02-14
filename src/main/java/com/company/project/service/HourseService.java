package com.company.project.service;

import com.company.project.model.Hourse;
import java.util.List;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface HourseService extends Service<Hourse> {
	List<Hourse> findHouse(String name,String phone);
	List<Hourse> authFamily(String name,String idCard);
}
