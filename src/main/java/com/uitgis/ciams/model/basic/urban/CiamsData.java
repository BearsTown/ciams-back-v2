package com.uitgis.ciams.model.basic.urban;

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
public class CiamsData {
    private Integer id;
    private String year;
    private String layerName;
    private String style;
    private int priority;
}
