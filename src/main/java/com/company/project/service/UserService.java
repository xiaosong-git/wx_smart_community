package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.User;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/02/11.
 */
public interface UserService extends Service<User> {

    Result verify(Long userId, String idNO, String name, String idHandleImgUrl, String localImgUrl);

    Result uploadPhoto(String userId, String mediaId, String type);

    User getUser(String openid);
}
