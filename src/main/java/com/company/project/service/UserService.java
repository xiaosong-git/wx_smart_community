package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.User;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface UserService extends Service<User> {

    Result verify(long userId, String idNO, String name, String idHandleImgUrl, String localImgUrl);

    Result uploadPhoto(String userId, String mediaId, String type) throws Exception;

    Map<String,Object> getUser(String openid);

    Result userHourseInfo(Long userId);
    
    List<User> findList(String name, String phone);

    List<User> finUserList(String name, String idCard);

    Result userAuthInfo(String openId);

    Result creatMenu();

    Result findStaff(String openId);
}
