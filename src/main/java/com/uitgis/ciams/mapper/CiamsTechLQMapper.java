package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsTechLQDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsTechLQMapper {
    public CiamsTechLQDto.HighTech.Find.Result selectHighTech(CiamsTechLQDto.HighTech.Find.Params params);

    public List<CiamsTechLQDto.TechLQ.Find.Result> selectTechLQ(CiamsTechLQDto.TechLQ.Find.Params params);
}
