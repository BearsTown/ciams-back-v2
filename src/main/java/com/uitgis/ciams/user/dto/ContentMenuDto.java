package com.uitgis.ciams.user.dto;

import com.uitgis.ciams.model.ContentMenu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContentMenuDto {
    public static class Find {

        @Getter
        @Setter
        @Builder
        public static class Params {
            private Integer parentId;
        }


        @Getter
        @Setter
        public static class Result extends ContentMenu {
            private List<Result> children;
        }
    }
}


