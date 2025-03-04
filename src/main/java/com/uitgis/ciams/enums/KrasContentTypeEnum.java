package com.uitgis.ciams.enums;

import lombok.Getter;

@Getter
public enum KrasContentTypeEnum {

    SHP("x-gis/x-shapefile"),
    SHX("x-gis/x-shapefile"),
    DBF("application/text"),
    PRJ("application/text"),
    ZIP("application/zip"),
    DEFAULT("application/octet-stream");


    private final String type;

    KrasContentTypeEnum(String type) {
        this.type = type;
    }
}
