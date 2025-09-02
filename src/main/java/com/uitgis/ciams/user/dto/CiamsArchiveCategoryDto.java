package com.uitgis.ciams.user.dto;

import com.uitgis.ciams.model.CiamsArchiveCategory;
import lombok.Getter;
import lombok.Setter;

public class CiamsArchiveCategoryDto {
	@Getter
	@Setter
	public static class Find {
		private String name;
	}

	@Getter
	@Setter
	public static class ListResult extends CiamsArchiveCategory {
	}

}
