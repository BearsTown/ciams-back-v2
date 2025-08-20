package com.uitgis.gis.dto;

import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.gis.model.GisCiamsZone;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GisCiamsZoneDTO {

    public static class Search {

        @Getter
        @Setter
        public static class Params extends PaginationDto {
            private String keyword;
            private String mngType;
        }

        @Getter
        @Setter
        public static class Row {
            private int rn;
            private String zoneNo;
            private String zoneName;
            private String mngType;
            private String useDist;
            private Double baseCsC;
            private Double zoneArea;
            private String itaResult;
            private String locResult;
        }

        @Getter
        @Setter
        @Builder
        public static class Result {
            private PaginationDto page; // 페이징 정보

            @Builder.Default
            private List<GisCiamsZoneDTO.Search.Row> list = new ArrayList<>();     // 검색 결과 리스트
        }
    }

    public static class Details {
        @Getter
        @Setter
        public static class Result extends GisCiamsZone {

        }
    }

}


