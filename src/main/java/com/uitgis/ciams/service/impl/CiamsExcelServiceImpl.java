package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsExcelColumnDto;
import com.uitgis.ciams.dto.CiamsExcelDataDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import com.uitgis.ciams.mapper.CiamsExcelMapper;
import com.uitgis.ciams.service.CiamsExcelService;
import com.uitgis.ciams.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsExcelServiceImpl implements CiamsExcelService {
    private final CiamsExcelMapper ciamsExcelMapper;


    @Override
    public List<String> getColumnNameList(String tableName) {
        return ciamsExcelMapper.selectColumnNameList(tableName);
    }

    @Override
    public List<CiamsExcelColumnDto.Find.Result> getColumnList(CiamsExcelColumnDto.Find.Params params) {
        return ciamsExcelMapper.selectColumnList(params);
    }


    @Override
    public CiamsExcelDataDto.Excel.Info getExcelDataList(CiamsExcelDataDto.Excel.Find.Params params) {
        if (params.getColumns() == null || params.getColumns().isEmpty()) {
            params.setColumns(getColumnNameList(params.getTableName()));
        }

        CiamsExcelColumnDto.Find.Params cps = CiamsExcelColumnDto.Find.Params
                .builder()
                .tableName(params.getTableName())
                .columns(params.getColumns())
                .build();

        return CiamsExcelDataDto.Excel.Info
                .builder()
                .tableName(cps.getTableName())
                .columns(getColumnList(cps))
                .data(ciamsExcelMapper.selectIncentiveExcel(null))
                .build();
    }

    @Override
    public void DownLoadIncen(HttpServletResponse response, CiamsPlanAreaIncenDto.Find.Incentive params) throws Exception {
        String tableName = "ciams_incentive";

        CiamsExcelColumnDto.Find.Params cps = CiamsExcelColumnDto.Find.Params
                .builder()
                .tableName(tableName)
                .columns(getColumnNameList(tableName))
                .build();

        CiamsExcelDataDto.Excel.Info excelInfo = CiamsExcelDataDto.Excel.Info
                .builder()
                .tableName(cps.getTableName())
                .columns(getColumnList(cps))
                .data(ciamsExcelMapper.selectIncentiveExcel(params))
                .build();

        Workbook workbook = ExcelUtil.Data2File(excelInfo);

        ExcelUtil.DownLoad(response, workbook);
    }


    @Override
    public void Data2File(CiamsExcelDataDto.Excel.Info info) {
        try {
            ExcelUtil.Data2File(info);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
