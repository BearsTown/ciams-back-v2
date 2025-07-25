package com.uitgis.ciams.mapper.classifyAnalysis;

import com.uitgis.ciams.dto.classifyAnalysis.ita.ClassItaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassItaMapper {
    /**
     * 산업기반분석 결과
     *
     * @param zoneNo
     * @return
     */
    List<ClassItaDto.ItaResultData> findAllItaResultDatasById(String zoneNo);
}
