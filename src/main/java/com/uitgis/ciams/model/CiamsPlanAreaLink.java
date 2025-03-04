package com.uitgis.ciams.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsPlanAreaLink {
	private String planAreaId;
	private String ver;
	private String planIncenId;
	private String planIncenLimitId;
	private String memo;
	private Date regDate;
	private Date chgDate;
	private String useYn;
	private String planContentLinkId;
}
