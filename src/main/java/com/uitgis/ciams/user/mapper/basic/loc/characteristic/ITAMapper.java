package com.uitgis.ciams.user.mapper.basic.loc.characteristic;

import com.uitgis.ciams.user.dto.basic.loc.characteristic.CharResultDto;
import com.uitgis.ciams.user.dto.basic.loc.characteristic.ItaDto;
import com.uitgis.ciams.user.dto.basic.loc.characteristic.StatusDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ITAMapper {
    /**
     * 산업기반분석 업종
     *
     * @param params
     * @return
     */
    int countItaData(ItaDto.Data.Search.Params params);


    /**
     * 산업기반분석 업종
     *
     * @param params
     * @return
     */
    List<ItaDto.Data.Search.Row> findAllItaDatas(ItaDto.Data.Search.Params params);


    /**
     * 대표산업
     *
     * @param params
     * @return
     */
    List<StatusDto.IndustryRep> findAllIndustryReps(StatusDto.IndustryStatus.Find.Params params);


    /**
     * 산업특성분석 결과
     *
     * @param params
     * @return
     */
    int countItaResultData(CharResultDto.Char.Search.Params params);


    /**
     * 산업특성분석 결과
     *
     * @param params
     * @return
     */
    List<CharResultDto.Char.Search.Row> findAllItaResultDatas(CharResultDto.Char.Search.Params params);


    /**
     * 인접 지자체 목록
     *
     * @param exclude
     * @return
     */
    List<ItaDto.Adjacent> findAllAdjacents(@Param("exclude") boolean exclude);
}

