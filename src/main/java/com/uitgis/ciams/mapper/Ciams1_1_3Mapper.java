package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.Ciams1_1_3Dto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Ciams1_1_3Mapper {
    public List<Ciams1_1_3Dto.ItaData> selectItaDatas(String sggCd);

    public List<Ciams1_1_3Dto.IndustryArea> selectIndustryAreas(@Param("exclude") boolean exclude);

    public int selectItaResultDataCount(Ciams1_1_3Dto.Search.Params params);

    public List<Ciams1_1_3Dto.Search.Row> selectItaResultDataList(Ciams1_1_3Dto.Search.Params params);

    public List<Ciams1_1_3Dto.IndustryStatusData> selectIndustryStatusDatas(Ciams1_1_3Dto.IndustryStatus.Find.Params params);

    public List<Ciams1_1_3Dto.IndustryRep> selectIndustryReps(Ciams1_1_3Dto.IndustryStatus.Find.Params params);
}
