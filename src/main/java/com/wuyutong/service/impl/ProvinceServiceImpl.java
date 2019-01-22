package com.wuyutong.service.impl;

import com.wuyutong.dao.ProvinceDOMapper;
import com.wuyutong.dataobject.ProvinceDO;
import com.wuyutong.error.BusinessException;
import com.wuyutong.error.EmBusinessError;
import com.wuyutong.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDOMapper provinceDOMapper;

    @Override
    public List<ProvinceDO> initProvince() throws BusinessException {
        List<ProvinceDO> provinceDOList = provinceDOMapper.selectAllProvince();
        if (provinceDOList == null && provinceDOList.isEmpty()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"省份不存在");
        }
        return provinceDOList;
    }
}
