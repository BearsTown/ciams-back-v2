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
public class CiamsAddress {
    private String sggCd;
    private String sggNm;
    private String emdCd;
    private String emdNm;
    private String riCd;
    private String riNm;
}
