package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    List<Map<String,Object>> userHourseInfo(Long userId);

    @Select("select * from "+ TableList.USER +" where id=#{userId} limit 1")
    User getUserFromId(Object userId);
    User getUserFromOpenId(String openId);


    List<User> findFamilyUser(Long hourseId);
}