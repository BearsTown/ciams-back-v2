package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;

public interface Menu2Service {
    public Menu2ZoneDetailsDto.Overview.Find.Result getMenu2Sub1OverView(Menu2ZoneDetailsDto.Overview.Find.Params params);
}
