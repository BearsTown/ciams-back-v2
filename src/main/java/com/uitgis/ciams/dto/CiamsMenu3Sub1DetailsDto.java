package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsPlanZoneImage;
import com.uitgis.ciams.model.CiamsPlanZoneIndustry;
import com.uitgis.ciams.model.CiamsZone;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CiamsMenu3Sub1DetailsDto {

    public static class Overview {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String zoneNo;
            }

            @Getter
            @Setter
            public static class Result extends CiamsZone {
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
