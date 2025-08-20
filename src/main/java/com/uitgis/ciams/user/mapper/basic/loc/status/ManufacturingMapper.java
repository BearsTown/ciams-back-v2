package com.uitgis.ciams.user.mapper.basic.loc.status;

import com.uitgis.ciams.user.dto.basic.loc.status.ManufacturingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManufacturingMapper {
    /**
     * 지역별 밀도 목록
     *
     * @param params
     * @return
     */
    List<ManufacturingDto.Density> findAllDensities(ManufacturingDto.Find.Params params);


    /**
     * 현황 목록
     *
     * @param params
     * @return
     */
    List<ManufacturingDto.Status> findAllStatuses(ManufacturingDto.Find.Params params);


    /**
     * 사업체수/종자자수 증감
     *
     * @param params
     * @return
     */
    List<ManufacturingDto.Increase> findAllIncreases(ManufacturingDto.Find.Params params);


    /**
     * 제조업 유형별 현황 목록
     *
     * @return
     */
    List<ManufacturingDto.CategoryStatus.Status> findAllCategoryStatuses();


    /**
     * 제조업 유형분류 목록
     *
     * @return
     */
    List<ManufacturingDto.CategoryStatus.CategoryGroup> findAllCategoryGroups();
}
