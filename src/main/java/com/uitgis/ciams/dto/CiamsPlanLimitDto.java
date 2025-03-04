package com.uitgis.ciams.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsPlanLimitDto {
    @Getter
    @Setter
    public static class Group {
    	private String planId;
        private String step1;
        private String step1nm;
        private String step2;
        private String step2nm;
        private String cd;
        private String cdnm;
    }
}
