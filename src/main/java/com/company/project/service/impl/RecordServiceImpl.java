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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
    Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);
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

    /**
     * 扫描当前管理员小区的二维码
     * @param opreWxId 管理员openId
     * @param idStr recordId
     * @param type 进出类型
     * @return 成功
     * @throws Exception
     */
    @Override
    public Result scanning(String opreWxId, String idStr, String type) throws Exception {
        String recordId = new String(Base64.decode(idStr),"UTF-8");
        //todo 判断是否为该小区用户
        System.out.println("操作者微信："+opreWxId);
        User operUser = userMapper.selectByopreWxId(opreWxId);//操作者
        String ext1 = operUser.getExt1();
        Long areaId =0L;
        boolean isArea=false;
        if (ext1 !=null&&!"".equals(ext1)) {
            areaId=  Long.valueOf(ext1);
        }
          //查找进出用户信息
            Map<String,Object> userMap = userMapper.getUserByRecordId(Long.valueOf(recordId));
            Map<String,Object> map=new HashMap<>();
        if (userMap != null) {
            Object userId = userMap.get("userId");
            if (userId !=null){
                    List<Area> areaById = areaMapper.findAreaById((long) userId);
                    for (Area area : areaById) {
                        //增加查询对方是否有该小区的次数
                        if(area.getId().equals(areaId)){
                            //查询小区进出剩余次数
                            Map<String, Object> time = areaMapper.areaTime(userId, area.getId());
                            if (time!=null){
                                BigDecimal day=new BigDecimal("1");
                                int count;//剩余次数
                                        BigDecimal areaDay=new BigDecimal(area.getFrequency());
                                        if(areaDay.compareTo(day) <= 0){
                                            // todo 查询 days天内 有多少通行记录
                                            //注意这里传参不同
                                            count = recordMapper.findCount(area.getId(), (long)userId, time.get("days"));
                                            if (count<=0){
                                                count=0;
                                            }
                                        }else{//注意这里传的是频率
                                            count = recordMapper.findCountElse(area.getId(), (long)userId, time.get("frequency"));
                                        }
                                        if (count<=0){
                                        return ResultGenerator.genFailResult("该用户在本小区进出次数已用尽");
                                        }
                                    }
                                }
                            System.out.println("对比用户小区："+area.getId()+" 管理员小区"+areaId);
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
                if (userMap.get("userId") !=null) {
                    records = hRecordMapper.selectByUserId(userMap.get("userId").toString());
                }
                map.put("user",userMap);
                map.put("records",records);
            return ResultGenerator.genSuccessResult(map);

    }

    @Override
    public Result inOut(Long opreId, String idStr, String type,String areaId) throws Exception {
        String recordId = new String(Base64.decode(idStr),"UTF-8");
        Record record = findById(Long.valueOf(recordId));
        if (record==null){
            return ResultGenerator.genFailResult("二维码不存在！");
        }
        record.setType(type);
        //判断是否已使用

        if(record.getAreaId()!=null&&!"".equals(record.getAreaId())){
            return ResultGenerator.genFailResult("二维码已失效！");
        }
        record.setAreaId(areaId);
        //判断用户二维码
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
    //查询用户进出记录
    public boolean selectRecord(Long userId) {

        //查询用户是否有小区
        List<Map<String,Object>> areas = areaMapper.areaTimes(userId);
        BigDecimal day=new BigDecimal("1");
        int total=0;
        int count=0;
        for (Map<String, Object> area : areas) {
                BigDecimal areaDay=new BigDecimal(area.get("frequency").toString());
                if(areaDay.compareTo(day) <= 0){
                    // todo 查询 days天内 有多少通行记录
                    //注意这里传参不同
                     count = recordMapper.findCount(area.get("id"), userId, area.get("days"));
                    if (count<=0){
                        count=0;
                    }
                    total+=count;
                }else{//注意这里传的是频率
                    count = recordMapper.findCountElse(area.get("id"), userId, area.get("frequency"));
                    total+=count;
                }
            }
        logger.info("总二维码次数:{},用户id：{}",total,userId);
        return total > 0;

    }



}


