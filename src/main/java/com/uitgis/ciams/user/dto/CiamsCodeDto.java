package com.uitgis.ciams.user.dto;

import com.uitgis.ciams.model.CiamsCode;

import lombok.Getter;
import lombok.Setter;

public class CiamsCodeDto {

    @Getter
    @Setter
    public static class Add extends CiamsCode {

    }

    @Getter
    @Setter
    public static class Find extends CiamsCode {
    	Boolean leaf;
    }

    @Getter
    @Setter
    public static class Modify extends CiamsCode {

    }

    @Getter
    @Setter
    public static class Sub {
        private String code;
        private String useYn;
        private Boolean useRoot;
    }
}
