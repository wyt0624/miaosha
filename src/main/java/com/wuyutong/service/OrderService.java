package com.wuyutong.service;

import com.wuyutong.error.BusinessException;
import com.wuyutong.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
