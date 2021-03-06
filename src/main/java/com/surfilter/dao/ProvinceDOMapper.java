package com.surfilter.dao;

import com.surfilter.dataobject.ProvinceDO;

import java.util.List;

public interface ProvinceDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProvinceDO record);

    int insertSelective(ProvinceDO record);

    ProvinceDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProvinceDO record);

    int updateByPrimaryKey(ProvinceDO record);

    List<ProvinceDO> selectAllProvince();
}