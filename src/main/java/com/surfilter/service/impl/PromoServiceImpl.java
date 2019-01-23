package com.surfilter.service.impl;

import com.surfilter.dao.PromoDOMapper;
import com.surfilter.dataobject.PromoDO;
import com.surfilter.error.BusinessException;
import com.surfilter.service.PromoService;
import com.surfilter.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) throws BusinessException {

        //获取秒杀活动信息
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        PromoModel promoModel = convertPromoModelFromPromoDO(promoDO);

        if (promoModel == null) {
            return null;
        }

        //判断秒杀活动状态
        if (promoModel.getStartTime().isAfterNow()){
            promoModel.setStatus(1);
        } else if (promoModel.getEndTime().isBeforeNow()){
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }
        return promoModel;
    }


    private PromoModel convertPromoModelFromPromoDO(PromoDO promoDO){
        if (promoDO == null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO,promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartTime(new DateTime(promoDO.getStartDate()));
        promoModel.setEndTime(new DateTime(promoDO.getEndDate()));
        return promoModel;
    }
}
