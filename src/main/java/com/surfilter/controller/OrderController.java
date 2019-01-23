package com.surfilter.controller;

import com.surfilter.controller.viewobject.OrderVO;
import com.surfilter.error.BusinessException;
import com.surfilter.error.EmBusinessError;
import com.surfilter.response.CommonReturnType;
import com.surfilter.service.OrderService;
import com.surfilter.service.model.OrderModel;
import com.surfilter.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/createOrder",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,@RequestParam(name = "amount")Integer amount,@RequestParam(name = "promoId")Integer promoId) throws BusinessException {

        //获取用户登录信息
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || isLogin == false){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        //创建订单
        OrderModel orderModel = orderService.createOrder(userModel.getId(),itemId,promoId,amount);
        OrderVO orderVO = ConvertOrderVOFromOrderModel(orderModel);
        return CommonReturnType.create(orderVO);
    }

    private OrderVO ConvertOrderVOFromOrderModel(OrderModel orderModel){
        OrderVO orderVO = new OrderVO();
        //将OrderModel属性拷贝到OrderVO
        BeanUtils.copyProperties(orderModel,orderVO);
        return orderVO;
    }



}
