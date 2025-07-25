package com.uitgis.ciams.dto.plan.concept;

import com.uitgis.ciams.model.CiamsPlanZoneImage;
import com.uitgis.ciams.model.CiamsPlanZoneIndustry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ConceptDto {

    public static class Info {
        public static class Find {
            @Getter
            @Setter
            public static class Result {
                private String zoneNo;
                private String zoneName;
                private String mngCd;
                private String mngType;

                private List<BaseDescDTO> baseDescs;
                private List<ZoneDescDTO> zoneDescs;
                private List<ZoneImageDTO> zoneImages;
                private List<ZoneIndustryDTO> zoneIndustries;
            }
        }
    }


    @Getter
    @Setter
    public static class BaseDescDTO {
        private String category;
        private List<String> list;
    }


    @Getter
    @Setter
    public static class ZoneDescDTO {
        private String category;
        private List<String> list;
    }


    @Getter
    @Setter
    public static class ZoneIndustryDTO {
        private String category;
        private List<CiamsPlanZoneIndustry> list;
    }


    @Getter
    @Setter
    public static class ZoneImageDTO {
        private String category;
        private List<CiamsPlanZoneImage> list;
    }

}
