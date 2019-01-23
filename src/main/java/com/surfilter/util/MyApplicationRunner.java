package com.surfilter.util;

import com.surfilter.dao.ProvinceDOMapper;
import com.surfilter.dataobject.ProvinceDO;
import com.surfilter.error.BusinessException;
import com.surfilter.error.EmBusinessError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private ProvinceDOMapper provinceDOMapper;

    public static List<ProvinceDO> provinceList = new ArrayList<>();

    //初始化省份 将省份加载到内存中
    public void initProvince() throws BusinessException {
        List<ProvinceDO> provinceDOList = provinceDOMapper.selectAllProvince();
        if(provinceDOList != null && !provinceDOList.isEmpty()){
            for (ProvinceDO provinceDO : provinceDOList) {
                provinceList.add(provinceDO);
            }
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"省份不存在");
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initProvince();
    }
}
