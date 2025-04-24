package com.uitgis.ciams.mapper;

import com.uitgis.ciams.model.CiamsData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataMapper {
    public List<CiamsData> selectDatas(int dataGroupId);
}
