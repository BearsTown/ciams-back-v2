package com.uitgis.ciams.mapper.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.TechLQDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TechLQMapper {
    /**
     * 집적도 높은 산업
     *
     * @param params
     * @return
     */
    TechLQDto.HighTech.Find.Result findAllHighTech(TechLQDto.HighTech.Find.Params params);


    /**
     * 기술업종목록
     *
     * @param params
     * @return
     */
    List<TechLQDto.TechLQ.Find.Result> findAllTechLQs(TechLQDto.TechLQ.Find.Params params);
}
