package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.AreaMapper;
import com.company.project.dao.RecordMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Area;
import com.company.project.model.Record;
import com.company.project.model.User;
import com.company.project.service.ParamsService;
import com.company.project.service.RecordService;
import com.company.project.util.Base64;
import com.company.project.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
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
    private AreaMapper areaMapper;
    @Resource
    private ParamsService paramsService;

    @Resource
    private RecordMapper recordMapper;

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
    public Result scanning(String opreWxId, String idStr, String type) throws Exception {
        String recordId = new String(Base64.decode(idStr),"UTF-8");
        //todo 判断是否为该小区用户
         //
        User operUser = userMapper.selectByopreWxId(opreWxId);
        String ext1 = operUser.getExt1();
        Long areaId =0L;
        boolean isArea=false;
        if (ext1 !=null&&!"".equals(ext1)) {
            areaId=  Long.valueOf(ext1);
        }
        //查找用户信息
            Map<String,Object> userMap = userMapper.getUserByRecordId(Long.valueOf(recordId));

            Map<String,Object> map=new HashMap<>();
            if (userMap != null) {
                if (userMap.get("userId")!=null){
                    List<Area> areaById = areaMapper.findAreaById((long)userMap.get("userId"));
                    for (Area area : areaById) {
                        if(area.getId().equals(areaId)){
                            isArea=true;
                            break;
                        }
                    }
                }
                if (!isArea){
                    return ResultGenerator.genFailResult("不是本小区用户");
                }
                if (userMap.get("imgUrl")!=null){
                    String imageServerUrl = paramsService.findValueByName("imageServerUrl");
                    userMap.put("imgUrl",imageServerUrl + File.separator+userMap.get("imgUrl"));
                }
                List<Record> records=new LinkedList<>();
                if (userMap.get("userId")!=null) {
                    records = hRecordMapper.selectByUserId(userMap.get("userId").toString());
                }

                map.put("user",userMap);
                map.put("records",records);

            }

            return ResultGenerator.genSuccessResult(map);

    }

    @Override
    public Result inOut(Long opreId, String idStr, String type) throws Exception {
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

            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("操作失败！");
    }

    public boolean selectRecord(Long userId) {
        //目前还未考虑多小区的问题

        //查询用户是否有小区
        int times = hRecordMapper.selectTimes(userId);

        if (times>0){
            return true;
        }else{
            return false;
        }

    }
}
