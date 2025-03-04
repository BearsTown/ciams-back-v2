package com.uitgis.ciams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsPlanContentDetail {
	// 성장관리계획 운영 컨텐츠 디테일_고유번호
	String planContentDetailId;
	// 성장관리계획 운영 컨텐츠_고유번호
	String planContentId;
	// 타입분류 (권장용도, 허용용도, 불허용도)
	String typeName;
	// 내용
	String contents;
	// 정렬순번
	Integer sortSn;
}
