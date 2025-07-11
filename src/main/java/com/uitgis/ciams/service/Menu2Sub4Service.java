package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.Menu2Sub4DetailsDto;
import com.uitgis.ciams.model.CiamsImage;

public interface Menu2Sub4Service {
    public Menu2Sub4DetailsDto.Overview.Find.Result getMenu2Sub4OverView(Menu2Sub4DetailsDto.Overview.Find.Params params);

    public Menu2Sub4DetailsDto.Info getAnalysisInfo(String zoneNo);

    public CiamsImage getImage(int id);
}
