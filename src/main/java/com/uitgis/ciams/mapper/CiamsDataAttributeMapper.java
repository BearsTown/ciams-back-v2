package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsMenu2Sub2Dto;
import com.uitgis.ciams.model.CiamsDataAttribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataAttributeMapper {
    public List<CiamsDataAttribute> selectDataAttributes(int dataGroupId);
}
