package com.uitgis.ciams.admin.dto;

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


    @Getter
    @Setter
    public static class Add {
        private Integer parentId;
        private String title;
        private boolean useYn;
    }


    @Getter
    @Setter
    public static class Update extends Add {
        private Integer id;
    }
}


