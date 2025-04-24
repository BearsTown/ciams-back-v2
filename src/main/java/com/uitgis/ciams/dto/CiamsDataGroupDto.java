package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsDataGroup;
import com.uitgis.ciams.model.CiamsPlanZoneImage;
import com.uitgis.ciams.model.CiamsPlanZoneIndustry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CiamsDataGroupDto {

    public static class Find {

        @Getter
        @Setter
        public static class Params {
            private Integer parentId;
        }

        @Getter
        @Setter
        public static class Result extends CiamsDataGroup {
//            private List<BaseDescDTO> baseDescs;
//            private List<ZoneDescDTO> zoneDescs;
//            private List<ZoneIndustryDTO> zoneIndustries;
//            private List<ZoneImageDTO> zoneImages;
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
