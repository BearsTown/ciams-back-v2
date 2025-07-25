package com.uitgis.gis.dto;

import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.gis.model.GisCiamsDist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GisCiamsDistDTO {

    public static class Search {

        @Getter
        @Setter
        public static class Params extends PaginationDto {
            private String keyword;
        }

        @Getter
        @Setter
        public static class Row {
            private int rn;
            private String distNo;
            private String distName;
            private Double distArea;
        }

        @Getter
        @Setter
        @Builder
        public static class Result {
            private PaginationDto page; // 페이징 정보

            @Builder.Default
            private List<GisCiamsDistDTO.Search.Row> list = new ArrayList<>();     // 검색 결과 리스트
        }
    }


    public static class Details {
        @Getter
        @Setter
        public static class Result extends GisCiamsDist {

        }
    }

}


