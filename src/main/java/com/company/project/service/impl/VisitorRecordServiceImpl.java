package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.VisitorRecordMapper;
import com.company.project.model.Area;
import com.company.project.model.User;
import com.company.project.model.VisitorRecord;
import com.company.project.service.VisitorRecordService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/03/04.
 */
@Service
@Transactional
public class VisitorRecordServiceImpl extends AbstractService<VisitorRecord> implements VisitorRecordService {
    @Resource
    private VisitorRecordMapper hVisitorRecordMapper;

    @Override
    public List<User> findByAreaidPhone(Long areaId, String phone) {
        List<User> user = hVisitorRecordMapper.findByAreaIdPhone(areaId,phone);

        return user;
    }

    @Override
    public List<Map<String,Object>> findApplying(Long userId) {

        return hVisitorRecordMapper.findApplying(userId);
    }

    @Override
    public int updateCstatus(Long recordId,String cstatus) {
        return hVisitorRecordMapper.updateCstatus(recordId,cstatus);
    }

    @Override
    public Long findRecordId(Long userId, Long visitorId, String visitDate, String visitTime) {
        return hVisitorRecordMapper.findRecordId(userId,visitorId,visitDate,visitTime);
    }

    @Override
    public List<Map<String, Object>> findUserByRecordId(Long recordId) {
        return hVisitorRecordMapper.findUserByRecordId(recordId);
    }
}
