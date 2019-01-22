package com.wuyutong.service.impl;

import com.wuyutong.dao.ProgramDOMapper;
import com.wuyutong.dao.PromoDOMapper;
import com.wuyutong.dataobject.ProgramDO;
import com.wuyutong.error.BusinessException;
import com.wuyutong.error.EmBusinessError;
import com.wuyutong.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramDOMapper programDOMapper;

    @Override
    //根据省份信息获取程序列表
    public List<ProgramDO> getProgramList(Integer provinceId) throws BusinessException {
        List<ProgramDO> programDOList = programDOMapper.selectByProvinceId(provinceId);
        if (programDOList == null && programDOList.isEmpty()){
            return null;
        }
        return programDOList;
    }

    @Override
    //根据省份获取程序总数
    public int getProgramTotal(Integer provinceId) {
        try {
            int num = programDOMapper.selectTotalByProvinceId(provinceId);
            return num;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public void addProgram(ProgramDO programDO) throws BusinessException {
        try {
            programDOMapper.insertSelective(programDO);

        } catch (Exception e){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"添加失败");
        }
    }

    @Override
    public void deleteProgram(Integer id) throws BusinessException {
        try {
            programDOMapper.deleteByPrimaryKey(id);
        } catch (Exception e){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"删除失败");
        }

    }

}