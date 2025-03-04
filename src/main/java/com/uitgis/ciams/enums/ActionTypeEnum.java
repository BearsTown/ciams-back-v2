package com.uitgis.ciams.enums;

public enum ActionTypeEnum {
	ADD("추가"), UPDATE("수정"), DELETE("삭제"), PRINT("출력");
	
	private String name;
	ActionTypeEnum(String name) {
        this.name = name;
    }
	public String getName() {
        return name;
    }
	
}
