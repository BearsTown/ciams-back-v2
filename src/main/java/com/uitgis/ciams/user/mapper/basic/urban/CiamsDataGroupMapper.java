package com.uitgis.ciams.user.mapper.basic.urban;

import com.uitgis.ciams.model.basic.urban.CiamsDataGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsDataGroupMapper {
    List<CiamsDataGroup> findAllDataGroupsById(int parentId);
}
