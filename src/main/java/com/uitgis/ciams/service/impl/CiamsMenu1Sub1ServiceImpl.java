package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsMenu2Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsMenu2Sub2Dto;
import com.uitgis.ciams.dto.CiamsTechLQDto;
import com.uitgis.ciams.mapper.CiamsDataAttributeMapper;
import com.uitgis.ciams.mapper.CiamsDataColumnMapper;
import com.uitgis.ciams.mapper.CiamsDataGroupMapper;
import com.uitgis.ciams.mapper.CiamsDataMapper;
import com.uitgis.ciams.mapper.CiamsTechLQMapper;
import com.uitgis.ciams.model.CiamsDataGroup;
import com.uitgis.ciams.service.CiamsMenu1Sub1Service;
import com.uitgis.ciams.service.CiamsMenu2Sub1Service;
import com.uitgis.gis.mapper.GisMapper;
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
