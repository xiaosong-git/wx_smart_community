package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Record;

import java.io.UnsupportedEncodingException;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface RecordService extends Service<Record> {

    Result createRecord(Long userId) throws UnsupportedEncodingException;

    Result scanning(String opreWxId, String idStr, String type) throws Exception;

    Result inOut(Long opreId, String idStr, String type,String areaId) throws Exception;
}
