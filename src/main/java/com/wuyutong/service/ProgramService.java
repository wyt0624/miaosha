package com.wuyutong.service;

import com.wuyutong.dataobject.ProgramDO;
import com.wuyutong.dataobject.ProvinceDO;
import com.wuyutong.error.BusinessException;

import java.util.List;

public interface ProgramService {
    //根据省份获取该省所有程序
    List<ProgramDO> getProgramList(Integer provinceId) throws BusinessException;

    //根据省份获取该省所有的程序数
    int getProgramTotal(Integer provinceId);

    //添加程序
    void addProgram(ProgramDO programDO) throws BusinessException;

    //删除程序
    void deleteProgram(Integer id) throws BusinessException;
}