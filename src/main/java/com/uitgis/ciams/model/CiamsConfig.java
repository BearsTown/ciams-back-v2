package com.uitgis.ciams.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CiamsConfig implements Serializable {
	private static final long serialVersionUID = -192548853096837227L;

	private String id;

    private String confName;

    private String confValue;

    private String confType;

    private Boolean used;

    private String confValueType;

    private String confDesc;

    private String regDate;

    private String chgDate;

    private String confUrl;
}
