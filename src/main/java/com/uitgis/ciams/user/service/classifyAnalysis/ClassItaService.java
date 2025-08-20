package com.uitgis.ciams.user.service.classifyAnalysis;

import com.uitgis.ciams.user.dto.classifyAnalysis.ita.ClassItaDto;

import java.util.List;

public interface ClassItaService {
    /**
     * 산업기반분석 결과
     *
     * @param zoneNo
     * @return
     */
    List<ClassItaDto.ItaResultData> getItaResultDataById(String zoneNo);
}
