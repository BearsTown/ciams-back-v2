package com.uitgis.ciams.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsArchive implements Serializable {
    private String archiveId;

    /**
     * 제목
     */
    private String title;

    /**
     * 내용
     */
    private String contents;

    private Integer numOfRead;

    private String categoryId;

    /**
     * 숨기 여부
     */
    private Boolean hidden;

    private String regUser;

    private String regDate;

    private String chgUser;

    private String chgDate;

    private static final long serialVersionUID = 1L;
}
