package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.AccessRecord;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AccessRecordMapper extends Mapper<AccessRecord> {

    @Select("select * from "+ TableList.ACCESSRECORD +" where personType = #{personType}")
    List<AccessRecord> findAllByPersonType(String personType);

    List<Map<String,Object>> findUserAccessArea(Long userId, Long areaId);
}