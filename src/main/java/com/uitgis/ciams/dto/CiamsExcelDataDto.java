package com.uitgis.ciams.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

public class CiamsExcelDataDto {
    @Getter
    @Setter
    public static class Data extends LinkedHashMap<String, Object> {
//        public Data() {
//            this.put("columnId", "");
//            this.put("createDate", new Date());
//            this.put("updateDate", new Date());
//        }
    }

    public static class Excel {
        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Info {
            private String tableName;
            private List<Data> data;
            private List<CiamsExcelColumnDto.Find.Result> columns;
        }

        public static class Find {
            @Getter
            @Setter
            @Builder
            public static class Params {
                private String tableName;
                private List<String> columns;
            }
        }
    }


}
