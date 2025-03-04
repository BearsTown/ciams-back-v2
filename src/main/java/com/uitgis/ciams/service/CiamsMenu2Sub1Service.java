package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu1StepCDetailsDto;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto;
import com.uitgis.ciams.dto.CiamsMenu2Sub1DetailsDto;

public interface CiamsMenu2Sub1Service {
    public CiamsMenu2Sub1DetailsDto.Overview.Find.Result getMenu2Sub1OverView(CiamsMenu2Sub1DetailsDto.Overview.Find.Params params);
}
