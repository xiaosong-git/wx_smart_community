package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Family;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface FamilyService extends Service<Family> {


    Result findFamilyUser(Long hourseId);


    Result addFamilyNameIdNo(Long hourseId, String userName, String idNo, Long userId);

    Result deleteFamilyUser(Long hourseId, Long userId);

    Result editFamilyUser(String userName, String idNo, Long userId);

    List<Family> findByUser(String name, String idCard);
}
