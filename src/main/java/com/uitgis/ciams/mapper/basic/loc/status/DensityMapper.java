package com.uitgis.ciams.mapper.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.DensityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DensityMapper {
    /**
     * 사업체 밀도변화 목록
     *
     * @param type
     * @return
     */
    List<DensityDto.Density> findDensityByType(String type);
}
