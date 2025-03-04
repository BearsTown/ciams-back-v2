package com.uitgis.ciams.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsBdRegDto {
	public static class Search {
		@Getter
		@Setter
		public static class param {
			private String pnu;
		}

		@Getter
		@Setter
		public static class Row {
			private String bdRegId;
			private String regTypeNm;
			private String pnu;
			private String sidoNm;
			private String sggNm;
			private String umdNm;
			private String dongNm;
			private String landTypeNm;
			private String jibunJu;
			private String jibunBu;
			private String address;
			private String subParcel;
			private String flArea;
			private String grFlArea;
			private String landArea;
			private String perDate;
			private String conDate;
			private String appuseDate;
			private String addresRoad;
			private String mainPurposeNm;
			private String etcPurposeNm;
			private String roofNm;
			private String etcRoofNm;
			private String floor;
			private String basement;
		}
	}
}
