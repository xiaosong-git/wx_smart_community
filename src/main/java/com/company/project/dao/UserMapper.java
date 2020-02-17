package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    Map<String,Object> getUserByRecordId(Long id) ;

    List<Map<String,Object>> userHourseInfo(Long userId);

    @Select("select * from "+ TableList.USER +" where id=#{userId} limit 1")
    User getUserFromId(Object userId);

    User getUserFromOpenId(String openId);

    List<User> findFamilyUser(Long hourseId);

    List<User> findList(@Param("name") String name, @Param("phone") String phone);

    List<User> findUserList(@Param("name") String name,@Param("idCard") String idCard);

    @Select("select * from "+ TableList.USER +" where id_no=#{idNoMW} and is_manager=0 limit 1")
    User findByIdNo(String idNoMW);

    @Select("select * from "+ TableList.USER +" where id_no=#{idNoMW} and name=#{name} limit 1")
    User findByIdNoName(String idNoMW,String name);
    @Select("select * from "+ TableList.USER +" where wx_open_id=#{openId} limit 1")
    User getUserOpenId(String openId);
    @Select("select * from "+ TableList.USER +" where name=#{userName} and id_no=#{idNo} limit 1")
    User findUserIdNo(String userName, String idNo);
    //查询管理员的小区
    @Select("select * from "+ TableList.USER+" where wx_open_id=#{opreWxId} limit 1")
    User selectByopreWxId(String opreWxId);
    //查询管理员信息
    @Select("select DISTINCT wx_open_id from "+TableList.USER+" where is_manager=0 and wx_open_id is not null and wx_open_id<>''")
    List<User> findManager();
    //查询员工信息
    @Select("select * from "+TableList.USER+" u left join "+TableList.STAFF+" s " +
            "on s.user_id=u.id  where s.area_id=#{ext1}  and s.status<>'delete' ")
    List <User> selectStaffUserByArea(String ext1);
    //根据姓名手机号查找用户
    @Select("select * from "+ TableList.USER+ " where name=#{name} and phone=#{phone} limit 1")
    User findByNamePhone(String name, String phone);
    //查找二级管理员信息
    @Select("select * from "+TableList.USER+" u left join "+TableList.STAFF+" s on u.id=s.user_id where  wx_open_id is not null")
    List<User> findStaff();
}