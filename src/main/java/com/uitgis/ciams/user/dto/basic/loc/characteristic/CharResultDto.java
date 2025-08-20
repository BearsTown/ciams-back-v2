package com.uitgis.ciams.user.dto.basic.loc.characteristic;

import com.uitgis.ciams.user.dto.PaginationDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CharResultDto {

    public static class Char {
        public static class Search {
            @Getter
            @Setter
            public static class Params extends PaginationDto {
                private String sggCd;
            }

            @Getter
            @Setter
            public static class Row extends CharResultData {
                private int rn;
            }

            @Getter
            @Setter
            @Builder
            public static class Result {
                private PaginationDto page; // 페이징 정보

                @Builder.Default
                private List<Row> list = new ArrayList<>();     // 검색 결과 리스트
            }
        }
    }


    @Getter
    @Setter
    public static class CharResultData {
        private String tCode;
        private String clsCode;
        private String codeNm;
        private String insNm;
        private Double lqValue;
        private String lqClass;
        private Double grValue;
        private String grClass;
        private Double rsValue;
        private String rsClass;
        private String itaRec;
        private String lqRec;
        private Integer corpCnt;
        private Integer workerCnt;
    }

}

