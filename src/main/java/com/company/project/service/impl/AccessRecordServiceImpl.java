package com.company.project.service.impl;

import com.company.project.dao.AccessRecordMapper;
import com.company.project.model.AccessRecord;
import com.company.project.service.AccessRecordService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class AccessRecordServiceImpl extends AbstractService<AccessRecord> implements AccessRecordService {
    @Resource
    private AccessRecordMapper hAccessRecordMapper;

    @Override
    public List<AccessRecord> findAllByPersonType(String personType) {

        return hAccessRecordMapper.findAllByPersonType(personType);
    }

    @Override
    public List<Map<String, Object>> findUserAccessArea(Long userId, Long areaId) {
        return hAccessRecordMapper.findUserAccessArea(userId,areaId);

    }
}
