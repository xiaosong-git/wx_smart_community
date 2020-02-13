package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends Mapper<User> {
    @Select("select * from "+ TableList.USER +" where id=#{userId} limit 1")
    User getUserFromId(Object userId);
    User getUserFromOpenId(String openId);


}