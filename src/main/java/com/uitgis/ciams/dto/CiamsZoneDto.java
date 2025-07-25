package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsZone;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CiamsZoneDto {
    public static class Search {

        @Getter
        @Setter
        public static class Params extends PaginationDto {
            private String keyword;
            private String itaReCd; // 산업기반분석
            private String locReCd; // 지역여건분석
            private String dvsCd;   // 관리유형구분
            private String mngCd;   // 관리유형설정
        }

        @Getter
        @Setter
        public static class Row {
            private int rn;
            private String zoneNo;
            private String zoneName;
            private String itaResult;
            private String itaReCd;
            private String locResult;
            private String locReCd;
            private String dvsCd;
            private String dvsType;
            private String mngCd;
            private String mngType;
            private String useDist;
            private Double baseCsC;
            private Double zoneArea;
        }

        @Getter
        @Setter
        @Builder
        public static class Result {
            private PaginationDto page; // 페이징 정보

            @Builder.Default
            private List<CiamsZoneDto.Search.Row> list = new ArrayList<>();     // 검색 결과 리스트
        }
    }


    public static class Overview {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String zoneNo;
            }

            @Getter
            @Setter
            public static class Result extends CiamsZone {
            }
        }
    }

}


