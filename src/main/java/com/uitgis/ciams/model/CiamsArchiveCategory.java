package com.uitgis.ciams.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsArchiveCategory implements Serializable {
	private String categoryId;

    /**
     * 카테고리 이름
     */
    private String name;

    /**
     * 노출 우선 순위
     */
    private Integer sortSn;

    private String regUser;

    private String chgDate;

    private String chgUser;

    private String regDate;

    private Boolean isLink;

    private String linkUrl;

    private static final long serialVersionUID = 1L;

}
