package com.uitgis.ciams.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiamsPlanLImit {
	private String planId;
    private String step1;
    private String step1nm;
    private String step2;
    private String step2nm;
    private String cd;
    private String cdnm;
}
