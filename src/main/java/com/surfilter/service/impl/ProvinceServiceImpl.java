package com.surfilter.service.impl;

import com.surfilter.dao.ProvinceDOMapper;
import com.surfilter.dataobject.ProvinceDO;
import com.surfilter.error.BusinessException;
import com.surfilter.error.EmBusinessError;
import com.surfilter.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
