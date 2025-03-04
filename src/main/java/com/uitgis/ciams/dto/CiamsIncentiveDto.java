package com.uitgis.ciams.dto;

import java.util.List;
import com.uitgis.ciams.model.CiamsIncentiveLoc;

import lombok.Getter;
import lombok.Setter;

public class CiamsIncentiveDto {

	public static class Search {
		public static class Find {
			@Getter
			@Setter
			public static class Params {
				private Integer state;
				private String pmsNo;
				private String regNo;
				private String perm;
				private String licBuilding;
				private String licBuildingNm;
				private String licDevelop;
				private String licDevelopNm;
				private String emd;
				private String ri;
				private Boolean mountain;
				private String mainJibun;
				private String subJibun;
				private Integer pageNo;
				private Boolean paged;
			}

			@Getter
			@Setter
			public static class IncenSearchList {
				private String incentiveId;
				private String pmsNo;
				private String regNo;
				private String licBuilding;
				private String licBuildingNm;
				private String licDevelop;
				private String licDevelopNm;
				private String loc;
				private String state;
				private String regDate;
			}
		}
	}

	public static class TargetLand {
		public static class Find {
			@Getter
			@Setter
			public static class Params {
				private String incentiveId;
			}

			@Getter
			@Setter
			public static class Result {
				private String incentiveId;
				private String pmsNo;
				private String regNo;
				private String perm;
				private String area;
				private String areaUseCd;
				private String areaUseNm;
				private String licDevelop;
				private String licDevelopNm;
				private String licBuilding;
				private String licBuildingNm;
				private String struc;
				private String strucNm;
				private String department;
				private List<Loc> incentiveLocs;
			}
		}
	}

	@Getter
	@Setter
	public static class Add {
		private String incentiveId;
		private String regNo;
		private String area;
		private String areaUseCd;
		private String areaUseNm;
		private String licDevelop;
		private String licDevelopNm;
		private String licBuilding;
		private String licBuildingNm;
		private String struc;
		private String strucNm;
		private String status;
		private String regDate;
		private String delYn;
		private String perm;
		private String department;
		private List<Loc> incentiveLocs;
	}

	@Getter
	@Setter
	public static class Loc extends CiamsIncentiveLoc {
	}

	@Getter
	@Setter
	public static class PNUIncentive {
		private String incentiveId;
		private Integer area;
		private String regNo;
		private String repLoc;
		private String strucNm;
		private String areaUseNm;
		private String licDevelopNm;
		private String licBuildingNm;
		private Object summary;
	}
}
