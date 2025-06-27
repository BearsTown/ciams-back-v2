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
public class CiamsSourceGroup {
    private Integer id;
    private String category;
    private String targetId;
    private String targetDesc;
    private Integer sourceId;
    private String timePoint;
    private int priority;
}
