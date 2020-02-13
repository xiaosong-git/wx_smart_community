package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Family;

public interface FamilyMapper extends Mapper<Family> {
    Family findFamily(Long hourseId);
}