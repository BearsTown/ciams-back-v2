package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;

import java.util.List;

public interface CiamsSourceService {
    /**
     * 출처 목록
     *
     * @param params
     * @return
     */
    List<CiamsSourceGroupDto.Find.Result> getSources(CiamsSourceGroupDto.Find.Params params);
}
