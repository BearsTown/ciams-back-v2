package com.uitgis.ciams.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsPlanUseDto {
    @Getter
    @Setter
    public static class Group {
    	private String planId;
        private String key;
        private String value;
        private String title;
        private String cd;
    }
}
