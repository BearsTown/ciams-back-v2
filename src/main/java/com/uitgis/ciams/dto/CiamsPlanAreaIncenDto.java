package com.uitgis.ciams.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CiamsPlanAreaIncenDto {
    public static class Find {
        @Getter
        @Setter
        public static class Info {
            private String planId;
            private String areaType;
        }

        @Getter
        @Setter
        public static class Incentive {
            private String incentiveId;
            private String regNo;
            private String licBuilding;
            private String licDevelop;
           	private String emd;
           	private String ri;
           	private Boolean mountain;
           	private String mainJibun;
           	private String subJibun;

           	private Integer state;
        }
    }

    @Getter
    @Setter
    public static class AreaTypeItems {
        private List<AreaIncen> bcrItems;
        private List<AreaIncen> farItems;
    }

    @Getter
    @Setter
    public static class AreaIncen {
        private String areaUseCd;
        private String areaUseNm;
        private Integer standard;
        private Integer limit;
        private List<IncentiveItem> incentiveItems;
    }

    @Getter
    @Setter
    public static class IncentiveItem {
        private String gCategoryNm;
        private String mCategoryNm;
        private String sCategoryNm;
        private String formulaNm;
        private String increaseNm;
    }

    @Getter
    @Setter
    public static class AreaIncenIncrease {
        private String areaUseCd;
        private String areaUseNm;
        private String sCategory;
        private String sCategoryNm;
        private String title;
        private boolean applyYn;
        private String targetCategory;
        private String targetCategoryNm;
    }

    @Getter
    @Setter
    public static class Incentive {
        private String incentiveId;
        private String regNo;
        private String licBuilding;
        private String licDevelop;
        private String status;
        private String area;
        private String struc;
        private String planId;
        private String planArea_id;
        private String xy;
        private String regDate;
        private String regUser;
        private String delYn;
        private String areaUseCd;
        private String areaUseNm;
        private Integer state;
        private List<IncentiveLoc> incentiveLocs;
    }

    @Getter
    @Setter
    public static class IncentiveLoc {
        private String incentiveLocId;
        private String incentiveId;
        private String pnu;
        private String loc;
        private String jimok;
        private Integer area;
        private String rep;
        private String xy;
    }


}
