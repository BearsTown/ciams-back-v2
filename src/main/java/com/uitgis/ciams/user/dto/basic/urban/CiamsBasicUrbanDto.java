package com.uitgis.ciams.user.dto.basic.urban;

import com.uitgis.ciams.model.basic.urban.CiamsBasicUrbanDescription;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CiamsBasicUrbanDto {
    public static class Info {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private int dataGroupId;
                private String targetId;
            }

            @Getter
            @Setter
            @Builder
            public static class Result {
                private List<CiamsBasicUrbanDescription> descriptions;
            }
        }
    }
}

