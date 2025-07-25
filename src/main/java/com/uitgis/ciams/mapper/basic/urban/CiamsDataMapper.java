package com.uitgis.ciams.mapper.basic.urban;

import com.uitgis.ciams.model.basic.urban.CiamsData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataMapper {
    List<CiamsData> findAllDataById(int dataGroupId);
}
