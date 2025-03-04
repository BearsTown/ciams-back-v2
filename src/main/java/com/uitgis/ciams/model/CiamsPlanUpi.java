package com.uitgis.ciams.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiamsPlanUpi {
    private String no;
    private Integer year;
    private String pnu;
    private String loc;
    private String jimok;
    private double area;
    private String purpose;
    private String useArea;
    private String note;
    private Integer planId;
    private String planName;
    private String planType;
}
