package com.uitgis.ciams.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsMonitoringDto {
	public static class Search {
		@Getter
		@Setter
		public static class PlanArea {
			private String areaId;
			private List<String> areaIds;
			private int year;
		}

		@Getter
		@Setter
		public static class District {
			private String emdCd;
			private List<String> emdCds;
			private int year;
		}

		@Getter
		@Setter
		public static class Parcel {
			private String emdCd;
			private List<String> emdCds;
		}
	}

	@Getter
	@Setter
	@Builder
	public static class BuildingTypeResult {
		private String bdUse;
		private int cnt;
	}

	@Getter
	@Setter
	public static class BuildingOldResult {
		private String bdOld;
		private int cnt;
	}

	@Getter
	@Setter
	public static class CommonResult {
		private String cd;
		private String cdnm;
		private int cnt;
	}
}
