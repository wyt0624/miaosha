package com.surfilter.dao;

import com.surfilter.dataobject.ProgramDO;

import java.util.List;

public interface ProgramDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProgramDO record);

    int insertSelective(ProgramDO record);

    ProgramDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProgramDO record);

    int updateByPrimaryKey(ProgramDO record);

    List<ProgramDO> selectByProvinceId(Integer provinceId);

    int selectTotalByProvinceId(Integer provinceId);
}