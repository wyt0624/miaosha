package com.wuyutong.controller;

import com.alibaba.druid.util.StringUtils;
import com.wuyutong.controller.viewobject.UserVO;
import com.wuyutong.error.BusinessException;
import com.wuyutong.error.EmBusinessError;
import com.wuyutong.response.CommonReturnType;
import com.wuyutong.service.UserService;
import com.wuyutong.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController{

    @Autowired
    private UserService userService;


    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户注册
    public CommonReturnType register(@RequestParam(name="telephone")String telephone,@RequestParam(name="otpCode")String otpCode,@RequestParam(name="gender")Integer gender,@RequestParam(name="age")Integer age) throws BusinessException {
        //验证手机号和对应otpcode相符合
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        if (!StringUtils.equals(otpCode,inSessionOtpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }
        //userService.register();
        //return CommonReturnType.create()
    }


    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telephone")String telephone){
        //需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);

        //将OTP验证码同对应用户的手机号相关联
        httpServletRequest.getSession().setAttribute(telephone,optCode);

        System.out.println("telephone:" + telephone + "&optCode:" + optCode);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {

        //调用service
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域模型对象转化为可供UI使用的viewobject
        UserVO userVO = convertFromModel(userModel);
        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }


}
