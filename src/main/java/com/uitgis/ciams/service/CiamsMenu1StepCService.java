package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu1StepCDetailsDto;

public interface CiamsMenu1StepCService {
    public CiamsMenu1StepCDetailsDto.Overview.Find.Result getOverView(CiamsMenu1StepCDetailsDto.Overview.Find.Params params);
}
