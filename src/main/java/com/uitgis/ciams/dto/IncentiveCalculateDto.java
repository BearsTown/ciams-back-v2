package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class IncentiveCalculateDto {
    @Getter
    @Setter
    public static class Details {
        private String incentiveId;
        private String calcId;
        private String calcName;
        private String memo;
        private Object incentives;
        private Object summary;
    }

    public static class Find {
        public static class Params {
            @Getter
            @Setter
            @Builder
            public static class Info {
                private String areaUseCode;
                private String incentiveId;
            }

            @Getter
            @Setter
            @Builder
            public static class Props {
                private String areaUseCd;
                private String incentiveNtfcNo;
            }
        }
    }

    @Getter
    @Setter
    public static class AreaIncentive {
        private String incentiveId;
        private String incentiveNtfcNo;
        private String areaUseCd;
        private String areaUseNm;
        private String areaUseCaseNm;
        private Integer standardBcr;
        private Integer standardFar;
        private Integer limitBcr;
        private Integer limitFar;
        private List<IncentiveItem> incentiveItems;
    }

    @Getter
    @Setter
    public static class IncentiveItem {
        private String fieldName;
        private String gCategoryNm;
        private String mCategoryNm;
        private String sCategoryNm;
        private boolean useFormula;
        private String formulaBcr;
        private String formulaFar;
        private Object formulaBcrParams; // json
        private Object formulaFarParams; // json
    }

    @Getter
    @Setter
    @Builder
    public static class IncentiveInfo {
        private List<Props> props;
//        private List<CiamsCode> areas;
        private AreaIncentive areaIncentive;
    }

    @Getter
    @Setter
    public static class Props {
        private String code;
        private String name;
        private double value;
    }

    @Getter
    @Setter
    public static class Insert {
        private String calcId;
        private String incentiveId;
        private String memo;
        private String areaUseCode;
        private Object incentives;
        private Object summary;
    }

    @Getter
    @Setter
    @Builder
    public static class Update {
        private String calcId;
        private Integer state;
    }

    @Getter
    @Setter
    @Builder
    public static class Delete {
        private String calcId;
    }
}
