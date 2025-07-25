package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsZoneDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsZoneMapper {
    /**
     * 대상지 목록
     *
     * @param params
     * @return
     */
    int countCiamsZone(CiamsZoneDto.Search.Params params);


    /**
     * 대상지 목록
     *
     * @param params
     * @return
     */
    List<CiamsZoneDto.Search.Row> findAllCiamsZones(CiamsZoneDto.Search.Params params);


    /**
     * 대상지 개요
     *
     * @param params
     * @return
     */
    CiamsZoneDto.Overview.Find.Result findCiamsZoneOverView(CiamsZoneDto.Overview.Find.Params params);
}
