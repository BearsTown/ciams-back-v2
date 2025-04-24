package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.Ciams1_1_2Dto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Ciams1_1_2Mapper {
    public Ciams1_1_2Dto.DensityInfo selectDensityInfos(String type);
}
