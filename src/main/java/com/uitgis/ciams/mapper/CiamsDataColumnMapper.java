package com.uitgis.ciams.mapper;

import com.uitgis.ciams.model.CiamsDataColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataColumnMapper {
    public List<CiamsDataColumn> selectDataColumns(int dataGroupId);
}
