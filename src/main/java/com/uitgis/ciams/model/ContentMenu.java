package com.uitgis.ciams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentMenu {
    private Integer id;
    private Integer parentId;
    private Integer level;
    private String title;
    private boolean useYn;
    private Integer priority;
}
