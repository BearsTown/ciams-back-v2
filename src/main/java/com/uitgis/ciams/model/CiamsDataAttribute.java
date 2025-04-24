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
public class CiamsDataAttribute {
    private Integer id;
    private String name;
    private String label;
    private String unit;
    private boolean useRatio;
    private String aggType;
    private String dataType;
    private String seriesType;
    private int priority;
}
