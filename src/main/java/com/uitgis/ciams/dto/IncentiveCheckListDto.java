package com.uitgis.ciams.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class IncentiveCheckListDto {
    public static class Find {
        @Getter
        @Setter
        @Builder
        public static class Params {
            private String incentiveId;
        }
    }

    @Getter
    @Setter
    public static class Row {
        private String id;
        private String title;
        private String createDate;
    }

    @Getter
    @Setter
    @Builder
    public static class Insert {
        private String id;
        private String incentiveId;
        private String title;
        private Object contents;
    }

    @Getter
    @Setter
    @Builder
    public static class Delete {
        private String id;
    }
}
