package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu2Sub4DetailsDto;
import com.uitgis.ciams.model.CiamsImage;

public interface CiamsMenu2Sub4Service {
    public CiamsMenu2Sub4DetailsDto.Overview.Find.Result getMenu2Sub4OverView(CiamsMenu2Sub4DetailsDto.Overview.Find.Params params);

    public CiamsMenu2Sub4DetailsDto.Info getAnalysisInfo(String zoneNo);

    public CiamsImage getImage(int id);
}
