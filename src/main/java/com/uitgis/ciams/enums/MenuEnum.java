package com.uitgis.ciams.enums;

public enum MenuEnum {
	LOGIN("로그인"),
	ARCHIVE("게시판"),
	ARCHIVE_CATEGORY("게시판 카테고리"),
	CODE("코드"),
	ACCESS("접근권한"),
	CONFIG("설정"),
	FILE("파일"),
	CONTENT("컨텐츠"),
	CONTENT_LINK("컨텐츠 연결"),
	USER("사용자"),
	PLAN_LAYER("계획 레이어"),
	GIS("공간데이터"),
	ETC("기타");

	private String name;
	MenuEnum(String name) {
        this.name = name;
    }
	public String getName() {
        return name;
    }
}
