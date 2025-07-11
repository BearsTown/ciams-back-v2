package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.Menu3Sub1DetailsDto;
import com.uitgis.ciams.model.CiamsImage;

public interface Menu3Sub1Service {
    public Menu3Sub1DetailsDto.Info.Find.Result getMenu3Sub1Info(Menu3Sub1DetailsDto.Info.Find.Params params);

    public CiamsImage getImage(int id);
}
