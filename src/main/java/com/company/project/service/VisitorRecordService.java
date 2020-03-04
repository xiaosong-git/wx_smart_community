package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Area;
import com.company.project.model.User;
import com.company.project.model.VisitorRecord;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/04.
 */
public interface VisitorRecordService extends Service<VisitorRecord> {

    List<User> findByAreaidPhone(Long areaId, String phone);
}
