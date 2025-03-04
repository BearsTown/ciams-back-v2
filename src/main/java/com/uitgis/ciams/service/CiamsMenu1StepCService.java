package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu1StepCDetailsDto;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto;

public interface CiamsMenu1StepCService {
    public CiamsMenu1StepCDto.Search.Result getPlanAreaList(CiamsMenu1StepCDto.Search.Params params);

    public CiamsMenu1StepCDto.Details.Result getPlanArea(CiamsMenu1StepCDto.Search.Detail detail);

    public CiamsMenu1StepCDetailsDto.Overview.Find.Result getOverView(CiamsMenu1StepCDetailsDto.Overview.Find.Params params);
}
