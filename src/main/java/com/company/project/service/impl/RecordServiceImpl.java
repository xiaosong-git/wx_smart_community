package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.RecordMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Record;
import com.company.project.model.User;
import com.company.project.service.ParamsService;
import com.company.project.service.RecordService;
import com.company.project.core.AbstractService;
import com.company.project.util.Base64;
import com.company.project.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
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
    @Resource
    private UserMapper userMapper;

    @Resource
    private ParamsService paramsService;

    @Override
    public Result createRecord(Long userId) throws UnsupportedEncodingException {
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
        String encode = Base64.encode(record.getId().toString().getBytes("UTF-8"));
        return ResultGenerator.genSuccessResult(encode);
    }

    @Override
    public Result scanning(Long operId, String idStr,String type) throws Exception {
        String s = new String(Base64.decode(idStr),"UTF-8");
        Record record =new Record();
        record.setId(Long.valueOf(s));
        record.setType(type);
        //判
        String systemTime = DateUtil.getSystemTime();
        record.setPassTime(systemTime);
        record.setUpdateTime(systemTime);
        record.setIsPass("0");
        int update = update(record);
        if (update>0){
            //查找用户信息

            User user = userMapper.getUserByRecordId(record.getId());
            List<Record> records=hRecordMapper.selectByUserId(user.getId());
            String imageServerApiUrl = paramsService.findValueByName("imageServerApiUrl");

            user.setImgUrl(imageServerApiUrl+user.getImgUrl());
            Map<String,Object> map=new HashMap<>();
            map.put("user",user);
            map.put("records",records);

            return ResultGenerator.genSuccessResult(map);
        }
        return ResultGenerator.genFailResult("操作失败");
    }

    public boolean selectRecord(Long userId) {
        //目前还未考虑多小区的问题
        int times = hRecordMapper.selectTimes(userId);
        if (times>0){
            return true;
        }else{
            return false;
        }

    }
}
