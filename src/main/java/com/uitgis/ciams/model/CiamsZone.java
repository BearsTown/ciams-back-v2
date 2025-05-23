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
    private String mngType;
    private String useDist;
    private Double baseCsC;
    private Double zoneArea;
}
