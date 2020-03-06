package com.company.project.service;
import com.company.project.model.AccessRecord;
import com.company.project.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface AccessRecordService extends Service<AccessRecord> {

    List<AccessRecord> findAllByPersonType(String personType);

    List<Map<String,Object>> findUserAccessArea(Long userId, Long areaId);
}
