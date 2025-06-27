package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.dto.Menu2Sub1DetailsDto;

import java.util.List;

public interface CiamsSourceService {
    public List<CiamsSourceGroupDto.Find.Result> getSources(CiamsSourceGroupDto.Find.Params params);
}
