package com.uitgis.mis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.dto.CiamsPlanMapLayerDto;
import com.uitgis.ciams.dto.CiamsPlanMapServiceDto;
import com.uitgis.mis.model.Dtsource;

@Mapper
public interface MisMapper {
    public List<Dtsource> selectDtsource(String userId);

    public List<CiamsPlanMapServiceDto> getMapService(CiamsPlanMapServiceDto params);
    public List<CiamsPlanMapLayerDto> getMapLayers(CiamsPlanMapLayerDto params);
}


