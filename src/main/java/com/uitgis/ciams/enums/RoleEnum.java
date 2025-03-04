package com.uitgis.ciams.enums;

public enum RoleEnum {

	ROLE_TEMP("R0000"),           	// 권한없음
    ROLE_USER("R0001"),         	// 조회
    ROLE_MANAGER("R1000"),         	// 편집
    ROLE_ADMIN("R9000");        	// 관리자

    private String type;

    RoleEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
