package com.uitgis.ciams.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 성장관리계획 운영 컨텐츠 (기반시설계획, 건축물 용도계획, 환경관리계획)
 * @author lee
 *
 */
@Getter
@Setter
public class CiamsPlanContent {
	String planContentId;
	// 탭분류 (INFRA - 기반시설계획, BUILDINGUSE - 건축물 용도계획, ENVIRONMENT - 환경관리계획)
	String category;
	// 제목
	String title;
	// 펼침 여부
	Boolean isOpen;
	// 도로계획 여부
	Boolean isRoadPlan;
	// 용도종류선택 여부
	Boolean isType;
}
