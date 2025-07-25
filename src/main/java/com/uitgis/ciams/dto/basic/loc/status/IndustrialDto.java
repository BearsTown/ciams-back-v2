package com.uitgis.ciams.dto.basic.loc.status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class IndustrialDto {

    public static class Find {
        @Getter
        @Setter
        public static class Params {
            private String category;
        }
    }


    @Getter
    @Setter
    @Builder
    public static class Info {
        private List<String> notes;
        private List<Status> statuses;
        private List<Density> densities;
    }


    @Getter
    @Setter
    public static class Density {
        private String name;
        private Double area;
        private int corpCnt;
        private int workerCnt;
        private Double corpDensity;
        private Double workerDensity;
        private int priority;
    }


    @Getter
    @Setter
    public static class Status {
        private String code;
        private String codeName;
        private int corpCnt1;
        private int corpCnt2;
        private int corpCnt3;
        private int workerCnt1;
        private int workerCnt2;
        private int workerCnt3;
    }

}
