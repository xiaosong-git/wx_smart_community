package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Record;
import com.company.project.core.Service;

import java.io.UnsupportedEncodingException;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface RecordService extends Service<Record> {

    Result createRecord(Long userId) throws UnsupportedEncodingException;

    Result scanning(Long opreId, String idstr,String type) throws UnsupportedEncodingException;
}
