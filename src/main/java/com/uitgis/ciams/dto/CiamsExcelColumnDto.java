package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsExcelColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CiamsExcelColumnDto {
    public static class Find {
        @Getter
        @Setter
        @Builder
        public static class Params {
            private String tableName;
            private List<String> columns;
        }

        @Getter
        @Setter
        public static class Result extends CiamsExcelColumn {
        }
    }

}
