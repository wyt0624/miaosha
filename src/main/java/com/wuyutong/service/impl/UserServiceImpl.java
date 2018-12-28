package com.wuyutong.service.impl;

import com.wuyutong.dao.UserDOMapper;
import com.wuyutong.dao.UserPasswordDOMapper;
import com.wuyutong.dataobject.UserDO;
import com.wuyutong.dataobject.UserPasswordDO;
import com.wuyutong.service.UserService;
import com.wuyutong.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        //根据ID查询用户
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO == null){
            return null;
        }
        //根据userId获取加密密码
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO,userPasswordDO);

    }

    public UserModel convertFromDataObject(UserDO userDo, UserPasswordDO userPasswordDO){

        if(userDo == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDo,userModel);

        if(userPasswordDO.getEncrptPassword() != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
