package com.uitgis.ciams.dto;

import java.util.List;

import com.uitgis.ciams.model.CiamsPlanContentLink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsPlanContentLinkDto {
	String category;
	String planAreaId;

	@Getter
	@Setter
	public static class Select extends CiamsPlanContentLink {
	}

	@Getter
	@Setter
	public static class Delete extends CiamsPlanContentLink {
	}

	@Getter
	@Setter
	public static class Modify extends CiamsPlanContentLink {
		String category;
	}

	@Getter
	@Setter
	public static class Add{
		List<String> planContentIds;
		String planContentLinkId;
		Integer sortSn;
		String planAreaId;
		String category;
		String planId;
		String ver;
	}
}
