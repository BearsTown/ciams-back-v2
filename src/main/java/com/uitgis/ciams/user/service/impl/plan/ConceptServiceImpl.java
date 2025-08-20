package com.uitgis.ciams.user.service.impl.plan;

import com.uitgis.ciams.user.dto.plan.concept.ConceptDto;
import com.uitgis.ciams.user.mapper.plan.ConceptMapper;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.user.service.plan.ConceptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConceptServiceImpl implements ConceptService {
    private final ConceptMapper conceptMapper;


    /**
     * 기본구상 대상지 상세정보
     *
     * @param zoneNo
     * @return
     */
    @Override
    public ConceptDto.Info.Find.Result getConceptInfo(String zoneNo) {
        return conceptMapper.findConceptInfo(zoneNo);
    }


    @Override
    public CiamsImage getImage(int id) {
        return conceptMapper.findImageById(id);
    }

}
