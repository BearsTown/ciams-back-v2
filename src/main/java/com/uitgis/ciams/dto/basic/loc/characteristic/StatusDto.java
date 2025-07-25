package com.uitgis.ciams.dto.basic.loc.characteristic;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class StatusDto {

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
                // image
                // notes
                private List<IndustryRep> industryReps;
                private List<CiamsSourceGroupDto.Find.Result> sources;
            }
        }
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

