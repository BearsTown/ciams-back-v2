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
public class CiamsDataGroup {
    private Integer id;
    private String name;
    private String groupColumn;
    private Boolean useNoneColumn;
    private String chartAxis;
    private int priority;
}
