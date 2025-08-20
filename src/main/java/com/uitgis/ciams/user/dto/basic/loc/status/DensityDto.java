package com.uitgis.ciams.user.dto.basic.loc.status;

import com.uitgis.ciams.user.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.model.basic.loc.CiamsBasicLocDescription;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DensityDto {

    @Getter
    @Setter
    public static class Density {
        private String type;
        private String year;
        private String image;
        private Integer priority;
    }

    @Getter
    @Setter
    @Builder
    public static class DensityInfo {
        private List<Density> densities;
        private List<CiamsBasicLocDescription> descriptions;
        private List<CiamsSourceGroupDto.Find.Result> sources;
    }

}

