package com.uitgis.ciams.dto.basic.loc.status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ManufacturingDto {

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
        private List<Increase> increases;
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


    @Getter
    @Setter
    public static class Increase {
        private String code;
        private String codeName;
        private int corpIncrease;
        private int workerIncrease;
    }



    public static class CategoryStatus {
        @Getter
        @Setter
        @Builder
        public static class Info {
            private List<CategoryStatus.Status> statuses;
            private List<CategoryStatus.CategoryGroup> categoryGroups;
        }

        @Getter
        @Setter
        public static class Status {
            private String groupCode;
            private String groupName;
            private int corpCnt1Sum;
            private int corpCnt2Sum;
            private int corpCnt3Sum;
            private int workerCnt1Sum;
            private int workerCnt2Sum;
            private int workerCnt3Sum;
        }

        @Getter
        @Setter
        public static class CategoryGroup {
            private String groupCode;
            private String groupName;
            private List<CategoryStatus.Category> categories;
        }

        @Getter
        @Setter
        public static class Category {
            private String code;
            private String codeName;
        }
    }

}
