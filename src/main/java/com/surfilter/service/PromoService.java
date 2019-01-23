package com.surfilter.service;

import com.surfilter.error.BusinessException;
import com.surfilter.service.model.PromoModel;


public interface PromoService {
    //获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId) throws BusinessException;
}
