package com.company.project.service.impl;

import com.company.project.dao.RecordMapper;
import com.company.project.model.Record;
import com.company.project.service.RecordService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
@Service
@Transactional
public class RecordServiceImpl extends AbstractService<Record> implements RecordService {
    @Resource
    private RecordMapper hRecordMapper;

}
