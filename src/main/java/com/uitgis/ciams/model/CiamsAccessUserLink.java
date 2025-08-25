package com.uitgis.ciams.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CiamsAccessUserLink {
	private String userId;
	private String accessRoleCode;
	private String regDate;
	private String regUser;
}
