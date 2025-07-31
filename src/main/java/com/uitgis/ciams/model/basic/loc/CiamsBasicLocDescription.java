package com.uitgis.ciams.model.basic.loc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsBasicLocDescription {
    private Integer id;
    private String category;
    private String type;
    private String typeDesc;
    private String description;
    private int priority;
}
