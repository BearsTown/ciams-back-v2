package com.uitgis.ciams.user.service.plan;

import com.uitgis.ciams.user.dto.plan.concept.ConceptDto;
import com.uitgis.ciams.model.CiamsImage;

public interface ConceptService {
    /**
     * 기본구상 대상지 상세정보
     *
     * @param zoneNo
     * @return
     */
    ConceptDto.Info.Find.Result getConceptInfo(String zoneNo);

    CiamsImage getImage(int id);
}
