package com.uitgis.ciams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsCode {
	private String code;
	private String parentCode;
	private String codeName;
	private Integer sortSn;
	private String codeVal;
	private String codeDesc;
	private String useYn;

}
