package com.uitgis.ciams.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CiamsLoginLogDto {

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Add{
		private String loginId;
		private String ip;
		private String loginDate;
	}

	@Getter
	@Setter
	public static class Find extends PaginationDto{
		private String startDate;
		private String endDate;
	}

	@Getter
	@Setter
	public static class GroupFind{
		private String day;
		private Integer cnt;
	}

	@Getter
	@Setter
	@ToString
	public static class LoginStatByDate {
		private String accessDate;
		private int total;
	}
}
