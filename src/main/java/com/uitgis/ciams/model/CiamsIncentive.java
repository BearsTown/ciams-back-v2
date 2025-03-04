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
public class CiamsIncentive {
    private String incentiveId;
    private String seqNo;
    private String pmsNo;
    private String regNo;
    private String licBuilding;
    private String licDevelop;
    private String licBuildingNm;
    private String licDevelopNm;
    private String status;
    private String perm;
    private String permNm;
    private String area;
    private String struc;
    private String strucNm;
    private String planId;
    private String planAreaId;
    private String xy;
    private String regDate;
    private String regUser;
    private String delYn;
    private String areaUseCd;
    private String areaUseNm;
    private String department;
}
