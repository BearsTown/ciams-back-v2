package com.uitgis.ciams.user.dto.classifyAnalysis.ita;

import lombok.Getter;
import lombok.Setter;

public class ClassItaDto {

    @Getter
    @Setter
    public static class Info {
//        private String zoneNo;
//        private String zoneName;
    }


    @Getter
    @Setter
    public static class ItaResultData {
        private String tCode;
        private String clsCode;
        private String insNm;
        private Double lqValue;
        private Double grValue;
        private Double rsValue;
        private String itaRec;
        private String lqRec;
        private int baseCorpCnt;
        private int baseEmpCnt;
        private int baseEmpAvg;
        private int pastCorpCnt;
        private int pastEmpCnt;
        private int pastEmpAvg;
    }

}
