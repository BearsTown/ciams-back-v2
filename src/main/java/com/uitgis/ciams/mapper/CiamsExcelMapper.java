package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsExcelColumnDto;
import com.uitgis.ciams.dto.CiamsExcelDataDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsExcelMapper {
    public List<String> selectColumnNameList(String tableName);

    public List<CiamsExcelColumnDto.Find.Result> selectColumnList(CiamsExcelColumnDto.Find.Params params);

    public List<CiamsExcelDataDto.Data> selectIncentiveExcel(CiamsPlanAreaIncenDto.Find.Incentive params);
}
