package com.uitgis.ciams.user.mapper.basic.loc.general;

import com.uitgis.ciams.user.dto.basic.loc.general.GeneralDataDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GeneralDataMapper {
    /**
     * 일반현황 데이터 Item 목록
     *
     * @return
     */
    List<GeneralDataDto.DataItem> findAllGeneralDataItems();


    /**
     * 데이터 그룹 목록
     *
     * @param statusId 일반현황 데이터 ID
     * @return
     */
    List<GeneralDataDto.DataGroup> findAllDataGroupsById(int statusId);


    /**
     * 데이터 테이블
     *
     * @param dataId 데이터 ID
     * @return
     */
    GeneralDataDto.DataTable findDataTableById(int dataId);


    /**
     * 데이터 컬럼 목록
     *
     * @param dataId 데이터 ID
     * @return
     */
    List<GeneralDataDto.DataColumn> findAllDataColumnsById(int dataId);


    /**
     * 데이터 전체 레코드
     *
     * @param dataName 테이블명
     * @return
     */
    List<Map<String, Object>> findAllRecordsByName(String dataName);


    /**
     * 피벗 데이터 전체 레코드
     *
     * @param params 피벗 매개변수
     * @return
     */
    List<Map<String, Object>> findAllPivotRecords(GeneralDataDto.PivotParams params);


    /**
     * 피벗 속성
     *
     * @param dataId 데이터 ID
     * @return
     */
    GeneralDataDto.Pivot findPivotById(int dataId);


    /**
     * 피벗 컬럼 목록
     *
     * @param map
     * @return
     */
    List<GeneralDataDto.PivotColumn> findAllPivotByIdColumns(Map<String, String> map);
}
