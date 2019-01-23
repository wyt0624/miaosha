package com.surfilter.service;

import com.surfilter.dataobject.ProvinceDO;
import com.surfilter.error.BusinessException;

import java.util.List;

public interface ProvinceService {
    //加载省份到内存中
    List<ProvinceDO> initProvince() throws BusinessException;
}
