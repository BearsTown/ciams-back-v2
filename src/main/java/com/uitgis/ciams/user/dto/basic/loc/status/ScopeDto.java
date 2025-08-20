package com.uitgis.ciams.user.dto.basic.loc.status;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ScopeDto {

    @Getter
    @Setter
    public static class Data {
        private String category;
        private List<CategoryItem> categoryItems;
    }

    @Getter
    @Setter
    public static class CategoryItem {
        private String code;
        private String codeNm;
        private List<Code> children;
    }

    @Getter
    @Setter
    public static class Code {
        private String code;
        private String codeNm;
    }

}
