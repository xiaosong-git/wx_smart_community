package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.ParamsMapper;
import com.company.project.model.Params;
import com.company.project.service.ParamsService;
import com.company.project.util.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/12.
 */
@Service
@Transactional
public class ParamsServiceImpl extends AbstractService<Params> implements ParamsService {
    @Resource
    private ParamsMapper tblParamsMapper;

    @Override
    public String findValueByName(String paramName) {
        String value = null;
        value = findValueByNameFromRedis(paramName);
        //缓存中不存在，就从数据库中取值，并把值存入缓存中
        if (value == null){
            value = findValueByNameFromDB(paramName);
            if(value != null){
                RedisUtil.setStr("params_" + paramName, value, 32, null);
            }
        }
        return value;
    }
    /**
     * 从缓存中获取参数
     * @param paramName 参数名
     * @return
     */
    private String findValueByNameFromRedis(String paramName){
        String value = RedisUtil.getStrVal("params_" + paramName,32);
        return value;
    }
    /**
     * 从数据库中获取系统参数
     * @param paramName
     * @return
     */
    private String findValueByNameFromDB(String paramName){
        Params param = findBy("paramname", paramName);
        if( param != null){
            String value =param.getParamtext();
            return value;
        }
        return null;
    }
}
