package com.uitgis.ciams.user.mapper.plan;

import com.uitgis.ciams.user.dto.plan.concept.ConceptDto;
import com.uitgis.ciams.model.CiamsImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConceptMapper {
    /**
     * 기본구상 대상지 상세정보
     *
     * @param zoneNo
     * @return
     */
    ConceptDto.Info.Find.Result findConceptInfo(String zoneNo);

    CiamsImage findImageById(int id);
}
