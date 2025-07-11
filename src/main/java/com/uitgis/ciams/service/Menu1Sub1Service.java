package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsTechLQDto;

import java.util.List;

public interface Menu1Sub1Service {
    public CiamsTechLQDto.HighTech.Find.Result getHighTech(CiamsTechLQDto.HighTech.Find.Params params);

    public List<CiamsTechLQDto.TechLQ.Find.Result> getTechLQ(CiamsTechLQDto.TechLQ.Find.Params params);
}
