package com.uitgis.ciams.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiamsPlanMasterSummary {
	private String planId;
	private String useArea; 
	private String useAreaArea; 
	private String planAreaArea; 
	private String planAreaType1Cnt; 
	private String planAreaType2Cnt; 
	private String planAreaType3Cnt; 
	private String planAreaType4Cnt;
	private int planAreaCnt;
	private int planAreaCatagory1;
	private int planAreaCatagory2;
	private float planAreaRate;	
}
