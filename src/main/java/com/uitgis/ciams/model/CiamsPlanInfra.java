package com.uitgis.ciams.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/***
 * 기반시설계획관리
 * @return
 */
@Getter
@Setter
public class CiamsPlanInfra {
	//기반시설계획관리_고유번호
	String planInfraId;
	//성장관리구역고유번호
	String planAreaId;
	//지정일자
	Date planDate;
	//분류
	String category;
	//내용
	String contents;
}
