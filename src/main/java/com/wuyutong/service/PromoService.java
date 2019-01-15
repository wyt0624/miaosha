package com.wuyutong.service;

import com.wuyutong.error.BusinessException;
import com.wuyutong.service.model.PromoModel;

public interface PromoService {
    //获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId) throws BusinessException;
}
