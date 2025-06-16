package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsTechLQDto;
import com.uitgis.ciams.mapper.CiamsTechLQMapper;
import com.uitgis.ciams.service.CiamsMenu1Sub1Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CiamsMenu1Sub1ServiceImpl implements CiamsMenu1Sub1Service {
    private final CiamsTechLQMapper ciamsTechLQMapper;

    @Override
    public CiamsTechLQDto.HighTech.Find.Result getHighTech(CiamsTechLQDto.HighTech.Find.Params params) {
        return ciamsTechLQMapper.selectHighTech(params);
    }

    @Override
    public List<CiamsTechLQDto.TechLQ.Find.Result> getTechLQ(CiamsTechLQDto.TechLQ.Find.Params params) {
        return ciamsTechLQMapper.selectTechLQ(params);
    }
}
