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
public class CiamsSource {
    private Integer id;
    private String sourceOrgan;
    private String sourceService;
    private String title;
    private String sub;
}
