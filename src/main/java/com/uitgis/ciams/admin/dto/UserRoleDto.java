package com.uitgis.ciams.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserRoleDto {

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
	public static class Approve {
		String roleYn;
		List<String> userList;
	}

}
