package com.uitgis.ciams.user.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CiamsUserRoleDto {

	@Getter
	@Setter
	@Builder
	public static class Add {
		String userId;
		String userNm;
		String userRole;
		String deptNm;
		String roleYn;
	}

	@Getter
	@Setter
	public static class Approve{
		String roleYn;
		List<String> userList;
	}

}
