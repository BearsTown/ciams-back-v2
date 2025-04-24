package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsAnalysis;
import lombok.Getter;
import lombok.Setter;

public class CiamsMenu2Sub1DetailsDto {

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
}
