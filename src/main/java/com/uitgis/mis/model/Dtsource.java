package com.uitgis.mis.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dtsource {
    private String dtsourceId;
    private String userId;
    private String serviceNm;
    private String serviceTitle;
}
