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
public class CiamsIncentiveCalc {
    private String incentiveId;
    private String calcId;
    private String calcName;
    private String memo;
    private String areaUseCode;
    private Integer state;
    private String createDate;
    private Object incentives;
    private Object summary;
}
