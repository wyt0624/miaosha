package com.wuyutong.service;

import com.wuyutong.dataobject.ProvinceDO;
import com.wuyutong.error.BusinessException;

import java.util.List;

public interface ProvinceService {
    //加载省份到内存中
    List<ProvinceDO> initProvince() throws BusinessException;
}
