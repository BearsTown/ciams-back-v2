package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsPlanArea;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CiamsMenu1StepCDto {
    public static class Search {
    	@Getter
        @Setter
        public static class Detail {
            private String planId;
        }

        @Getter
        @Setter
        public static class Params extends PaginationDto {
            private String planId;
            private String name;
            private String remark;
        }

        @Getter
        @Setter
        public static class Row extends CiamsPlanArea {
            private int rn;
        }

        @Getter
        @Setter
        @Builder
        public static class Result {
            private PaginationDto page; // 페이징 정보

            @Builder.Default
            private List<CiamsMenu1StepCDto.Search.Row> list = new ArrayList<>();     // 검색 결과 리스트
        }
    }

    public static class Details {
        @Getter
        @Setter
        public static class Result extends CiamsPlanArea {

        }
    }
}


