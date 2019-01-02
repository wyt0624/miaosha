package com.wuyutong.service.impl;

import com.wuyutong.dao.UserDOMapper;
import com.wuyutong.dao.UserPasswordDOMapper;
import com.wuyutong.dataobject.UserDO;
import com.wuyutong.dataobject.UserPasswordDO;
import com.wuyutong.error.BusinessException;
import com.wuyutong.error.EmBusinessError;
import com.wuyutong.service.UserService;
import com.wuyutong.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public void register(UserModel userModel) throws BusinessException {
        if(userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if(StringUtils.isNotEmpty(userModel.getName()) || userModel.getGender() == null || userModel.getAge() == null || StringUtils.isEmpty(userModel.getTelephone())){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }


        //实现model -> dataobject方法
        UserDO userDO = new UserDO();
        userDOMapper.insertSelective(userDO);
        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return;
    }


    private UserPasswordDO convertPasswordFromModel(UserModel userModel){
        if(userModel == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }


    public UserDO convertFromModel(UserModel userModel){
        if(userModel == null) {
            return null;
        }
        UserDO userDo = new UserDO();
        BeanUtils.copyProperties(userModel,userDo);
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
