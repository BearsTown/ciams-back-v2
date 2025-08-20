package com.uitgis.ciams.user.service.classifyAnalysis;

import com.uitgis.ciams.user.dto.classifyAnalysis.comprehensive.ClassComprehensiveDto;
import com.uitgis.ciams.model.CiamsImage;

public interface ClassComprehensiveService {
    /**
     * 유형화종합분석 상세정보
     *
     * @param zoneNo
     * @return
     */
    ClassComprehensiveDto.Info getComprehensiveInfo(String zoneNo);

    CiamsImage getImage(int id);
}
