package com.surfilter.service;

import com.surfilter.error.BusinessException;
import com.surfilter.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
