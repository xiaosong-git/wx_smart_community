package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Area;
import com.company.project.model.User;
import com.company.project.model.VisitorRecord;
import com.company.project.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/03/04.
 */
public interface VisitorRecordService extends Service<VisitorRecord> {

    List<User> findByAreaidPhone(Long areaId, String phone);

    List<Map<String,Object>> findApplying(Long userId);

    int updateCstatus(Long recordId,String cstatus);

    Long findRecordId(Long userId,Long visitorId,String visitDate,String visitTime);

    List<Map<String,Object>> findUserByRecordId(Long recordId);
}
