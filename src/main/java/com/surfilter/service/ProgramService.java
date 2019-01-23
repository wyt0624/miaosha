package com.surfilter.service;

import com.surfilter.dataobject.ProgramDO;
import com.surfilter.error.BusinessException;
import com.surfilter.service.model.ProgramModel;

import java.net.UnknownHostException;
import java.util.List;

public interface ProgramService {
    //根据省份获取该省所有程序
    List<ProgramModel> getProgramList(Integer provinceId) throws BusinessException;

    //根据省份获取该省所有的程序数
    int getProgramTotal(Integer provinceId);

    //添加程序
    void addProgram(ProgramDO programDO) throws BusinessException;

    //删除程序
    void deleteProgram(Integer id) throws BusinessException;

    List<ProgramModel> getLogs(String id,String province_id) throws BusinessException, UnknownHostException;

    List<ProgramDO> selectAllProgram();
}
