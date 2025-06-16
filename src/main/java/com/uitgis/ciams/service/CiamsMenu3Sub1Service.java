package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu3Sub1DetailsDto;
import com.uitgis.ciams.model.CiamsImage;

public interface CiamsMenu3Sub1Service {
    public CiamsMenu3Sub1DetailsDto.Overview.Find.Result getMenu3Sub1OverView(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params);

    public CiamsImage getImage(int id);
}
