package com.uitgis.ciams.mapper.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.DensityDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DensityMapper {
    /**
     * 사업체 밀도변화 정보
     *
     * @param type
     * @return
     */
    DensityDto.DensityInfo findDensityInfoByType(String type);
}
