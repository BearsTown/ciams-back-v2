package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.Menu2Sub1DetailsDto;

import java.util.List;

public interface Menu2Sub1Service {
    public List<Menu2Sub1DetailsDto.ItaResultData> getByZoneNoItaResultDatas(String zoneNo);
}
