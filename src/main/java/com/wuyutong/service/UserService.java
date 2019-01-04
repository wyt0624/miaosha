package com.wuyutong.service;

import com.wuyutong.controller.viewobject.UserVO;
import com.wuyutong.error.BusinessException;
import com.wuyutong.service.model.UserModel;

public interface UserService {
    //通过用户ID获取用户信息
    UserModel getUserById(Integer id);
    //用户注册
    void register(UserModel userModel) throws BusinessException;
    //用户登录
    void login(UserModel userModel) throws BusinessException;
    /**
     * telephone : 用户手机号
     * password ：用户加密后的密码
     * */
    UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException;
}
