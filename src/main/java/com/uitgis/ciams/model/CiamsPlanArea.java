package com.uitgis.ciams.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiamsPlanArea {
    private String mnum;
    private String alias;
    private String remark;
    private String name;
    private Double csC;
    private Double area;
}
