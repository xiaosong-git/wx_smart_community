package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Record;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecordMapper extends Mapper<Record> {
    //查询记录是否次数
    int selectTimes(Long userId);

    Record findMyRecord(Long userId);
    @Select("select * from h_record where user_id =#{id} and is_pass=0 order by pass_time desc limit 4")
    List<Record> selectByUserId(String id);
}