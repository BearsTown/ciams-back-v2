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
public class CiamsIncentiveLoc {
    private String incentiveLocId;
    private String incentiveId;
    private String pnu;
    private String loc;
    private String jimok;
    private Integer area;
    private String rep;
    private String xy;
}
