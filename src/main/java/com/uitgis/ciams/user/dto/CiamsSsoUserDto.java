package com.uitgis.ciams.user.dto;

import java.util.List;

import com.uitgis.ciams.model.CiamsUserRole;
import com.uitgis.ciams.model.CiamsSsoUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CiamsSsoUserDto {

	@Getter
	@Setter
	public static class Add {
		String id;
		String name;
		String email;
		String password;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Select extends CiamsUserRole {
		private String userId;
		private String userName;
		private String userRole;
		private Boolean lock;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Data extends CiamsUserRole {
		private String groupCode;
	    private String parentGroupCode;
	    private String groupName;
	    private String isteam;
	    private String fullPath;
	    private String fullName;
	    private String userId;
	    private String loginId;
	    private String sortorder;
	    private String userName;
	    private String ssn;
	    private String clssNm;
	    private String positNm;
	    private String mobilephone;
	    private String officephone;
	    private String email;
	    private String description;
	    private String oukid;
	    private String regularity;
	    private String userPassword;
	    private int loginFailCnt;
	    private Boolean lock;

	    private PaginationDto paginationDto;
	}

	@Getter
	@Setter
	public static class Find extends PaginationDto{
		private String keyword;
		private String roleYn;			//승인여부
		private Boolean lock;
	}

	@Getter
	@Setter
	public static class Lock {
		private Boolean lock;
		private List<String> userList;
	}

	@Getter
	@Setter
	public static class Modify extends CiamsSsoUser {
		private String curPassword;
		private String newPassword;
		private String userRole;
	}

}
