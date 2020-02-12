package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.RecordMapper;
import com.company.project.model.Record;
import com.company.project.service.RecordService;
import com.company.project.core.AbstractService;
import com.company.project.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class RecordServiceImpl extends AbstractService<Record> implements RecordService {
    @Resource
    private RecordMapper hRecordMapper;

    @Override
    public Result createRecord(Long userId) {
        if (!selectRecord(userId)) {
            return ResultGenerator.genFailResult("今日次数用尽");
        }
        Record record = hRecordMapper.findMyRecord(userId);
        if (record==null){
            record=new Record();
            record.setUserId(userId);
            record.setCreateTime(DateUtil.getSystemTime());
            this.save(record);
        }
        return ResultGenerator.genSuccessResult(record);
    }

    public boolean selectRecord(Long userId) {
        int times = hRecordMapper.selectTimes(userId);
        if (times>0){
            return true;
        }else{
            return false;
        }

    }
}
