package com.uitgis.ciams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uitgis.gis.mapper.GisMapper;
import com.uitgis.ciams.dto.CiamsPlanUpiDto;
import com.uitgis.ciams.dto.CiamsPlanUpiDto.UseArea;
import com.uitgis.ciams.service.CiamsPlanUpiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CiamsPlanUpiServiceImpl implements CiamsPlanUpiService {
    @Autowired
    private GisMapper gismapper;

    @Override
    public List<CiamsPlanUpiDto.Summary.Year> getPlanUpiSummaryYear(CiamsPlanUpiDto.Summary.Year.Find find) {
        // use gisdb
        return gismapper.getPlanUpiSummaryYear(find);
    }

    @Override
    public List<CiamsPlanUpiDto.Summary.YearPurpose> getPlanUpiSummaryYearPurpose(String planId) {
        // use gisdb
        return gismapper.getPlanUpiSummaryYearPurpose(planId);
    }

    @Override
    public List<CiamsPlanUpiDto.Purpose> getPlanUpiPurpose(CiamsPlanUpiDto.Purpose.Find find) {
        // use gisdb
        return gismapper.getPlanUpiPurpose(find);
    }

    @Override
    public List<UseArea> getPlanUpiUseArea(CiamsPlanUpiDto.UseArea.Find find) {
        return gismapper.getPlanUpiUseArea(find);
    }

    @Override
    public List<CiamsPlanUpiDto.Jimok> getPlanUpiJimok(String planId) {
        return gismapper.getPlanUpiJimok(planId);
    }

}
