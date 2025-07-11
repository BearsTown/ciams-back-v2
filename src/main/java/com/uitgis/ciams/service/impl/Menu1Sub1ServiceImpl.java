package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsTechLQDto;
import com.uitgis.ciams.mapper.CiamsTechLQMapper;
import com.uitgis.ciams.service.Menu1Sub1Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Menu1Sub1ServiceImpl implements Menu1Sub1Service {
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
