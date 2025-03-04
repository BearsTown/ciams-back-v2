package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsPlanArea;
import lombok.Getter;
import lombok.Setter;

public class CiamsMenu1StepCDetailsDto {

    public static class Overview {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String planId;
                private String name;
            }

            @Getter
            @Setter
            public static class Result extends CiamsPlanArea {

            }
        }
    }
}
