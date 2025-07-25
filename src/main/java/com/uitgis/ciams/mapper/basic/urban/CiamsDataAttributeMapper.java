package com.uitgis.ciams.mapper.basic.urban;

import com.uitgis.ciams.model.basic.urban.CiamsDataAttribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataAttributeMapper {
    List<CiamsDataAttribute> findAllDataAttributesById(int dataGroupId);
}
