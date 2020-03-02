package com.company.project.service.impl;

import com.company.project.dao.VisitorRecordMapper;
import com.company.project.model.VisitorRecord;
import com.company.project.service.VisitorRecordService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/02.
 */
@Service
@Transactional
public class VisitorRecordServiceImpl extends AbstractService<VisitorRecord> implements VisitorRecordService {
    @Resource
    private VisitorRecordMapper hVisitorRecordMapper;

}
