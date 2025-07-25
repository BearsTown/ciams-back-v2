package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsSourceMapper {
    /**
     * 출처 목록
     *
     * @param params
     * @return
     */
    List<CiamsSourceGroupDto.Find.Result> findAllSources(CiamsSourceGroupDto.Find.Params params);
}
