package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.Ciams1_1_3Dto;
import com.uitgis.ciams.dto.Menu2Sub1DetailsDto;
import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Menu2Sub1Mapper {
    public List<Menu2Sub1DetailsDto.ItaResultData> selectByZoneNoItaResultDatas(String zoneNo);
}
