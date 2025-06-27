package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsSourceMapper {
    public List<CiamsSourceGroupDto.Find.Result> selectSources(CiamsSourceGroupDto.Find.Params params);
}
