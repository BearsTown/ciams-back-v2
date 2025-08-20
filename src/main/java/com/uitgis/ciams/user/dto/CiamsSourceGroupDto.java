package com.uitgis.ciams.user.dto;

import com.uitgis.ciams.model.CiamsSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CiamsSourceGroupDto {

    public static class Find {
        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Params {
            private String category;
            private String targetId;
        }

        @Getter
        @Setter
        public static class Result {
            private String category;
            private String targetId;
            private String targetDesc;
            private List<SourceDTO> sources;
        }
    }


    @Getter
    @Setter
    public static class SourceDTO {
        private Integer sourceId;
        private String timePoint;
        private Integer priority;
        private CiamsSource source;
    }

}
