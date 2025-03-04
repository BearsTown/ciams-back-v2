package com.uitgis.ciams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsAccessUserLink {
	private String userId;
	private String accessRoleCode;
	private String regDate;
	private String regUser;
}
