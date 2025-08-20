package com.uitgis.ciams.user.dto;

import com.uitgis.ciams.model.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDto {
    public static class Find {

        @Getter
        @Setter
        @Builder
        public static class Params {
            private Integer menuId;
        }


        @Getter
        @Setter
        public static class Result extends Content {
            //
        }
    }
}

