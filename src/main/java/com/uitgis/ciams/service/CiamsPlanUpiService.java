package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.dto.CiamsPlanUpiDto;

public interface CiamsPlanUpiService {
    /**
     * 개발행위허가
     */
    public List<CiamsPlanUpiDto.Summary.Year> getPlanUpiSummaryYear(CiamsPlanUpiDto.Summary.Year.Find find);

    public List<CiamsPlanUpiDto.Summary.YearPurpose> getPlanUpiSummaryYearPurpose(String planId);

    public List<CiamsPlanUpiDto.UseArea> getPlanUpiUseArea(CiamsPlanUpiDto.UseArea.Find find);

    public List<CiamsPlanUpiDto.Purpose> getPlanUpiPurpose(CiamsPlanUpiDto.Purpose.Find find);

    public List<CiamsPlanUpiDto.Jimok> getPlanUpiJimok(String planId);
}
