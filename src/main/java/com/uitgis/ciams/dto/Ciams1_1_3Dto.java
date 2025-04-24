package com.uitgis.ciams.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Ciams1_1_3Dto {

    @Getter
    @Setter
    public static class ItaData extends ItaResultData {
        private String sggCd;
        private String sggNm;
        private String category;
    }


    @Getter
    @Setter
    public static class IndustryArea {
        private String sggCd;
        private String sggNm;
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

    public static class IndustryStatus {
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
                private List<IndustryStatusData> IndustryStatusDatas;
                private List<IndustryRep> industryReps;
            }
        }


    }

    @Getter
    @Setter
    public static class IndustryStatusData {
        private String insNm;
        private String tcode;
        private String clsCode;
        private Double lqValue;
        private Double grValue;
        private Double rsValue;
        private String itaRec;
        private String lqRec;
        private Integer corpCnt;
        private Integer workerCnt;
    }

    @Getter
    @Setter
    public static class IndustryRep {
        private String insNm;
        private String tcode;
        private String clsCode;
        private String itaRec;
        private String lqRec;
        private String corpName;
        private String location;
        private Integer workerCnt;
    }
}

