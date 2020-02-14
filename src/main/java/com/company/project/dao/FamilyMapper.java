package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.Family;
import org.apache.ibatis.annotations.Insert;

public interface FamilyMapper extends Mapper<Family> {
    Family findFamily(Long hourseId);


}