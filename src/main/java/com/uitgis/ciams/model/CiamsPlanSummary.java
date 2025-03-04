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
public class CiamsPlanSummary {
	private String gubun;
	private String zoneNm;
	private String totalAr;
	private String developAr;
	private String ratio;
	private String totalCo;
	private String totalRt;
	private String indstCo;
	private String indstRt;
	private String edcSctyCo;
	private String edcSctyRt;
	private String etcCo;
	private String etcRt;
	private String comCo;
	private String comRt;
	private String rsdCo;
	private String rsdRt;
	private String useArea;
	private String planId;
	
}
