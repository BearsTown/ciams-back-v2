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
public class CiamsDataColumn {
    private Integer id;
    private String name;
    private String label;
    private String shorts;
    private boolean useRange;
    private String start;
    private String end;
    private String color;
}
