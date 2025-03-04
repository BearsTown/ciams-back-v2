package com.uitgis.ciams.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CiamsPlanUpiDto {
    @Getter
    @Setter
    public static class Purpose {
        private Integer cnt;
        private Integer area;
        private String purpose;

        @Getter
        @Setter
        public static class Find {
            private String planId;
            private Integer startYear;
            private Integer endYear;
        }
    }

    public static class Summary {
        @Getter
        @Setter
        public static class Year {
            private Integer cnt;
            private Integer area;
            private Integer year;

            @Getter
            @Setter
            public static class Find {
                private String planId;
                private Integer startYear;
                private Integer endYear;
                private List<String> purpose;
            }
        }

        @Getter
        @Setter
        public static class YearPurpose extends Purpose {
            private Integer year;
        }
    }

    @Getter
    @Setter
    public static class Jimok {
        private Integer cnt;
        private Integer area;
        private String jimok;
    }

    @Getter
    @Setter
    public static class UseArea {
        private Integer cnt;
        private Integer area;
        private String useArea;

        @Getter
        @Setter
        public static class Find {
            private String planId;
            private Integer startYear;
            private Integer endYear;
            private List<String> purpose;
        }
    }
}
