package com.uitgis.ciams.user.mapper.basic.loc.status;

import com.uitgis.ciams.user.dto.basic.loc.status.IndustrialDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndustrialMapper {
    /**
     * 지역별 밀도 목록
     *
     * @param params
     * @return
     */
    List<IndustrialDto.Density> findAllDensities(IndustrialDto.Find.Params params);


    /**
     * 현황 목록
     *
     * @param params
     * @return
     */
    List<IndustrialDto.Status> findAllStatuses(IndustrialDto.Find.Params params);
}
