package com.uitgis.ciams.controller;


import com.uitgis.ciams.dto.CiamsPlanUpiDto;
import com.uitgis.ciams.service.CiamsPlanUpiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/plan/upi")
@RestController
@RequiredArgsConstructor
public class CiamsPlanUpiContoller {
    private final CiamsPlanUpiService ciamsPlanUpiService;

    /**
     * 개발행위허가
     */
    @GetMapping("{planId}/getPurpose")
    public ResponseEntity<?> getPurpose(@PathVariable String planId, CiamsPlanUpiDto.Purpose.Find find) {
        find.setPlanId(planId.toLowerCase());
        List<CiamsPlanUpiDto.Purpose> result = ciamsPlanUpiService.getPlanUpiPurpose(find);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{planId}/getSummaryYear")
    public ResponseEntity<?> getSummaryYear(@PathVariable String planId, CiamsPlanUpiDto.Summary.Year.Find find) {
        find.setPlanId(planId.toLowerCase());
        List<CiamsPlanUpiDto.Summary.Year> result = ciamsPlanUpiService.getPlanUpiSummaryYear(find);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{planId}/getSummaryYearPurpose")
    public ResponseEntity<?> getSummaryYearPurpose(@PathVariable String planId) {
        List<CiamsPlanUpiDto.Summary.YearPurpose> result = ciamsPlanUpiService.getPlanUpiSummaryYearPurpose(planId.toLowerCase());
        return ResponseEntity.ok(result);
    }

    @GetMapping("{planId}/getJimok")
    public ResponseEntity<?> getJimok(@PathVariable String planId) {
        List<CiamsPlanUpiDto.Jimok> result = ciamsPlanUpiService.getPlanUpiJimok(planId.toLowerCase());
        return ResponseEntity.ok(result);
    }

    @GetMapping("{planId}/getSummaryUseArea")
    public ResponseEntity<?> getSummaryUseArea(@PathVariable String planId, CiamsPlanUpiDto.UseArea.Find find) {
        find.setPlanId(planId.toLowerCase());
        List<CiamsPlanUpiDto.UseArea> result = ciamsPlanUpiService.getPlanUpiUseArea(find);
        return ResponseEntity.ok(result);
    }
}
