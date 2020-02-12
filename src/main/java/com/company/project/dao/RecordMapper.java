package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Record;

import java.util.List;
import java.util.Map;

public interface RecordMapper extends Mapper<Record> {
    //查询记录是否次数
    int selectTimes(Long userId);

    Record findMyRecord(Long userId);
}