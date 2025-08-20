package com.uitgis.ciams.user.dto.basic.loc.characteristic;

import com.uitgis.ciams.user.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.model.basic.loc.CiamsBasicLocDescription;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ItaDto {

    public static class Info {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String type;
            }

            @Getter
            @Setter
            @Builder
            public static class Result {
                private List<CiamsBasicLocDescription> descriptions;
                private List<CiamsSourceGroupDto.Find.Result> sources;
            }
        }
    }

    public static class Data {
        public static class Search {
            @Getter
            @Setter
            public static class Params extends PaginationDto {
                private String type;
            }

            @Getter
            @Setter
            public static class Row extends ItaData {
                private int rn;
            }

            @Getter
            @Setter
            @Builder
            public static class Result {
                private PaginationDto page; // 페이징 정보

                @Builder.Default
                private List<ItaDto.Data.Search.Row> list = new ArrayList<>();     // 검색 결과 리스트
            }
        }
    }


    @Getter
    @Setter
    public static class ItaData extends ItaResultData {
        private String sggCd;
        private String sggNm;
        private String category;
    }


    @Getter
    @Setter
    public static class ItaResultData {
        private String insNm;
        private String tCode;
        private String clsCode;
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


    @Getter
    @Setter
    public static class Adjacent {
        private String sggCd;
        private String sggNm;
    }

}

