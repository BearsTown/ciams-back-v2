package com.uitgis.ciams.model.basic.urban;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsBasicUrbanDescription {
    private Integer id;
    private String category;
    private String dataGroupId;
    private String targetId;
    private String description;
    private int priority;
}
