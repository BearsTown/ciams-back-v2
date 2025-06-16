package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsAnalysis;
import com.uitgis.ciams.model.CiamsPlanZoneImage;
import com.uitgis.ciams.model.CiamsPlanZoneIndustry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CiamsMenu2Sub4DetailsDto {

    public static class Overview {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String zoneNo;
            }

            @Getter
            @Setter
            public static class Result extends CiamsAnalysis {
            }
        }
    }

    @Getter
    @Setter
    public static class Info {
        private String zoneNo;
        private String zoneName;

        private List<BaseDescDTO> descs;
        private List<ImageDTO> images;
        private List<IndustryDTO> industries;
    }

    @Getter
    @Setter
    public static class BaseDescDTO {
        private String category;
        private List<String> list;
    }

    @Getter
    @Setter
    public static class IndustryDTO {
        private String category;
        private List<CiamsPlanZoneIndustry> list;
    }

    @Getter
    @Setter
    public static class ImageDTO {
        private String category;
        private List<CiamsPlanZoneImage> list;
    }
}
