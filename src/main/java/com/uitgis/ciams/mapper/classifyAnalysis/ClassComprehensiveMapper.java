package com.uitgis.ciams.mapper.classifyAnalysis;

import com.uitgis.ciams.dto.classifyAnalysis.comprehensive.ClassComprehensiveDto;
import com.uitgis.ciams.model.CiamsImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassComprehensiveMapper {
    /**
     * 유형화종합분석 상세정보
     *
     * @param zoneNo
     * @return
     */
    ClassComprehensiveDto.Info findComprehensiveInfoById(String zoneNo);


    CiamsImage selectImage(int id);
}
