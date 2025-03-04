package com.uitgis.ciams.dto;


import com.uitgis.ext.kras.model.KrasLandInfo;
import com.uitgis.ext.kras.model.KrasLandUsePlanBase;
import com.uitgis.ext.kras.model.KrasLandUsePlanLegend;
import com.uitgis.ext.kras.model.KrasLandUsePlanRestrict;
import com.uitgis.ext.kras.model.KrasOfficialLandPrice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class KrasResultDto {


	private String code;
	private String message;

	private Object resultData;
	private String krasResultLabel;


	@Getter
	@Setter
	@NoArgsConstructor
	public static class ifAll{

		@Builder
		public ifAll(KrasLandInfo krasLandInfo
				, KrasOfficialLandPrice[] jigaList
				, KrasLandUsePlanBase krasLandUsePlanBase
				, KrasLandUsePlanRestrict[] landUsePlanRestrict) {
			this.krasLandInfo = krasLandInfo;
			this.jigaList = jigaList;
			this.krasLandUsePlanBase = krasLandUsePlanBase;
			this.landUsePlanRestrict = landUsePlanRestrict;
		}

		private KrasLandInfo krasLandInfo;
		private KrasOfficialLandPrice[] jigaList;
		private KrasLandUsePlanBase krasLandUsePlanBase;
		private KrasLandUsePlanLegend[] krasLandUsePlanLegends;
		private KrasLandUsePlanRestrict[] landUsePlanRestrict;
	}
}
