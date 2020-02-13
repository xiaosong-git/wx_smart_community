package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Family;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface FamilyService extends Service<Family> {


    Result findFamilyUser(Long hourseId);
}
