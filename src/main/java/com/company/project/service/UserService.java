package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.User;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface UserService extends Service<User> {

    Result verify(String openId, String idNO, String name, String idHandleImgUrl, String localImgUrl,String phone);

    Result uploadPhoto(String userId, String mediaId, String type) throws Exception;

    Model getUser(Model model, String openid);

    Result userHourseInfo(Long userId);
    
    List<User> findList(String name, String phone);

    List<User> finUserList(String name, String idCard);

    Result userAuthInfo(String openId);

    //根据管理员查询员工
    Result findStaff(String openId);

    Result creatUserToTag(int tagId);

    Result findByOpenId(String openId);
}
