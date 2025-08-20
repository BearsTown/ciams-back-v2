package com.uitgis.ciams.admin.dto;

import com.uitgis.ciams.model.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContentDto {
    public static class Find {

        @Getter
        @Setter
        @Builder
        public static class Params {
            private Integer id;
            private Integer menuId;
        }


        @Getter
        @Setter
        public static class Result extends Content {
            //
        }
    }

    @Getter
    @Setter
    public static class Add {
        private Integer id;
        private Integer menuId;
        private String title;
        private boolean useYn;
        private boolean useCollapse;
        private String content;
        private List<String> imageFileIds;
    }

    @Getter
    @Setter
    public static class Update extends Add {
        private Integer id;
    }

    @Getter
    @Setter
    public static class Priority {
        private Integer menuId;
        private List<Integer> ids;
    }
}

