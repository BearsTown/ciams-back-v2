package com.uitgis.ciams.dto.basic.loc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CiamsBasicLocDescriptionDto {
    public static class Find {
        @Getter
        @Setter
        @Builder
        public static class Params {
            private String type;
            private String category;
        }
    }
}

