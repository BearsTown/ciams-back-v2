package com.uitgis.ciams.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiamsAnalysis {
    private String mnum;
    private String alias;
    private String remark;
    private String name;
    private Double csC;
    private Double area;
    private Double density;
    private String densityRe;
    private Double variation;
    private String variaRe;
    private Double sssC;
    private Double sssRate;
    private String sssRe;
    private String itaResult;
    private String csB;
    private String csB20;
    private Double deterio;
    private String deterioRe;
    private Double roadA;
    private Double roadRate;
    private String roadRe;
    private String locala;
    private String fResult;
}
