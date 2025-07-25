package com.uitgis.ciams.service.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.TechLQDto;

import java.util.List;

public interface TechLQService {
    /**
     * 집적도 높은 기술
     *
     * @param params
     * @return
     */
    TechLQDto.HighTech.Find.Result getHighTech(TechLQDto.HighTech.Find.Params params);


    /**
     * 기술업종목록
     *
     * @param params
     * @return
     */
    List<TechLQDto.TechLQ.Find.Result> getTechLQs(TechLQDto.TechLQ.Find.Params params);
}
