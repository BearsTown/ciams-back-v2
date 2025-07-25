package com.uitgis.ciams.mapper.basic.urban;

import com.uitgis.ciams.model.basic.urban.CiamsDataColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataColumnMapper {
    List<CiamsDataColumn> findAllDataColumnsById(int dataGroupId);
}
