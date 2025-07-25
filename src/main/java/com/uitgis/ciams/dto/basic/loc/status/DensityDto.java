package com.uitgis.ciams.dto.basic.loc.status;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DensityDto {

    @Getter
    @Setter
    public static class Density {
        private String year;
        private String image;
        private Integer priority;
    }

    @Getter
    @Setter
    public static class DensityInfo {
        private String type;
//        @TypeHandler(JsonArrayTypeHandler.class)
        private List<String> notes;
        private List<Density> densities;
    }

}

