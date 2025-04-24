package com.uitgis.gis.model;


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
public class GisCiamsDist {
    private String distNo;
    private String distName;
    private Double distArea;
}
