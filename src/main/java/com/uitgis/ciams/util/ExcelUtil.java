package com.uitgis.ciams.util;

import com.uitgis.ciams.dto.CiamsExcelColumnDto;
import com.uitgis.ciams.dto.CiamsExcelDataDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtil {
    public static List<CiamsExcelDataDto.Data> File2Data(MultipartFile excel) throws Exception {
        String orgName = excel.getOriginalFilename();
        String fileExt = orgName.substring(orgName.lastIndexOf(".") + 1);

        log.info("orgName :: " + orgName);
        log.info("fileExt :: " + fileExt);

        List<CiamsExcelDataDto.Data> data = getExcelData(excel.getInputStream(), 0, 2);
        log.info(data.toString());

        return data;
    }

    public static Workbook Data2File(CiamsExcelDataDto.Excel.Info info) throws Exception {
        log.info("columns :: " + info.getColumns());
        log.info("data :: " + info.getData());

        Workbook wb = new XSSFWorkbook();

        try {
            int rowCount = 0;

            Sheet sheet = wb.createSheet(info.getTableName());
            sheet.createFreezePane(0, 2);

            Row headerNameRow = sheet.createRow(rowCount++);
            Row headerTitleRow = sheet.createRow(rowCount++);

            CellStyle headerStyle = wb.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            Font headerFont = wb.createFont();
            headerFont.setBold(true);

            headerStyle.setFont(headerFont);

            List<CiamsExcelColumnDto.Find.Result> columns = info.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                CiamsExcelColumnDto.Find.Result column = columns.get(i);

                Cell headerNameCell = headerNameRow.createCell(i);
                headerNameCell.setCellStyle(headerStyle);
                headerNameCell.setCellValue(column.getColumnName());
                Cell headerTitleCell = headerTitleRow.createCell(i);
                headerTitleCell.setCellStyle(headerStyle);
                headerTitleCell.setCellValue(column.getColumnAlias());

                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1024);
            }

            List<CiamsExcelDataDto.Data> dataList = info.getData();
            for (CiamsExcelDataDto.Data value : dataList) {
                Row row = sheet.createRow(rowCount++);

                for (int j = 0; j < columns.size(); j++) {
                    Object data = value.get(columns.get(j).getColumnName());

                    Cell cell = row.createCell(j);

                    if (data != null) {
                        if (data instanceof BigDecimal) {
                            cell.setCellValue(new BigDecimal(String.valueOf(data)).doubleValue());
                        } else if (data instanceof Double) {
                            cell.setCellValue((double) data);
                        } else if (data instanceof Integer) {
                            cell.setCellValue((int) data);
                        } else if (data instanceof Boolean) {
                            cell.setCellValue((boolean) data);
                        } else {
                            cell.setCellValue(data.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return wb;
    }

    public static List<CiamsExcelDataDto.Data> getExcelData(InputStream is, int headerRowNo, int dataRowNo)
            throws IOException {

        List<CiamsExcelDataDto.Data> resultList;

        try (Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            String[] headers = getHeader(sheet, headerRowNo);
            resultList = getData(sheet, headers, dataRowNo);
        }

        return resultList;
    }

    public static List<CiamsExcelDataDto.Data> getData(Sheet sheet, String[] headers, int dataRowNo) {
        List<CiamsExcelDataDto.Data> resultList = new ArrayList<>();

        int numOfData = sheet.getLastRowNum() - dataRowNo + 1;
        for (int i = 0; i < numOfData; i++) {
            Row row = sheet.getRow(i + dataRowNo);
            CiamsExcelDataDto.Data dataMap = getCellData(headers, row);

            resultList.add(dataMap);
        }

        return resultList;
    }

    public static String[] getHeader(Sheet sheet, int headerRowNo) {
        Row headerRow = sheet.getRow(headerRowNo);
        int numOfCell = headerRow.getLastCellNum();

        String[] result = new String[numOfCell];
        for (int i = 0; i < numOfCell; i++) {
            String cellVal = (String) getCellValue(headerRow.getCell(i));
            result[i] = cellVal;
        }

        return result;
    }

    public static Object getCellValue(Cell cell) {
        Object value;
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = objSimpleDateFormat.format(cell.getDateCellValue());
                } else {
                    value = cell.getNumericCellValue();

                    String[] strVal = String.valueOf(value).split("\\.");
                    if (strVal.length > 1 && Double.parseDouble(strVal[1]) == 0) {
                        value = ((Double) value).intValue();
                    }
                }
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case ERROR:
                value = cell.getErrorCellValue() + "";
                break;
            default:
                value = null;
                break;
        }
        return value;
    }

    private static CiamsExcelDataDto.Data getCellData(String[] headers, Row row) {
        CiamsExcelDataDto.Data dataMap = new CiamsExcelDataDto.Data();
        for (int i = 0; i < headers.length; i++) {
            dataMap.put(headers[i], getCellValue(row.getCell(i)));
        }

        return dataMap;
    }

    public static void DownLoad(HttpServletResponse response, Workbook workbook) throws Exception {
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + workbook.getSheetName(0) + ".xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
