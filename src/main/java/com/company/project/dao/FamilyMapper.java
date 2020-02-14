package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Family;

public interface FamilyMapper extends Mapper<Family> {
    Family findFamily(Long hourseId);
    List<Family> findByUser(@Param("name") String name,@Param("idCard") String idCard);
}