package com.uitgis.ciams.mapper;

import com.uitgis.ciams.model.CiamsDataGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataGroupMapper {
    public List<CiamsDataGroup> selectDataGroups(int parentId);
}
