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
public class CiamsZone {
    private String zoneNo;
    private String zoneName;
    private String useDist;
    private Double zoneArea;
    private String dvsType;
    private String dvsCd;
    private String mngType;
    private String mngCd;
    private Double localArea;
    private Integer localCs;
    private Integer baseCsC;
    private Double density;
    private String densityRe;
    private Integer pastCsC;
    private Integer variation;
    private String variaRe;
    private Integer sssC;
    private Double sssRate;
    private String sssRe;
    private String itaResult;
    private String itaReCd;
    private Integer csB;
    private Integer csB20;
    private Double deterio;
    private String deterioRe;
    private Double roadA;
    private Double roadRate;
    private String roadRe;
    private String locResult;
    private String locReCd;
    private String division;
    private String baseYear;
    private String pastYear;
}
