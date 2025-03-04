package com.uitgis.ciams.service;


import com.uitgis.ciams.dto.CiamsExcelColumnDto;
import com.uitgis.ciams.dto.CiamsExcelDataDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface CiamsExcelService {
    public List<String> getColumnNameList(String tableName);

    public List<CiamsExcelColumnDto.Find.Result> getColumnList(CiamsExcelColumnDto.Find.Params params);

    public CiamsExcelDataDto.Excel.Info getExcelDataList(CiamsExcelDataDto.Excel.Find.Params params);

    public void DownLoadIncen(HttpServletResponse response, CiamsPlanAreaIncenDto.Find.Incentive params) throws Exception;

    public void Data2File(CiamsExcelDataDto.Excel.Info info);
}
