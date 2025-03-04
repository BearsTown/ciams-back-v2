package com.uitgis.ciams.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.uitgis.ciams.model.CiamsPlanContent;
import com.uitgis.ciams.model.CiamsPlanContentDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsPlanContentDto {

	@Getter
	@Setter
	public static class SelectDetail extends CiamsPlanContent {
		String planContentLinkId;
		Integer sortSn;
		List<CiamsPlanContentDetailDto.Detail> detailList;
//		List<CiamsFile> fileList;
	}

	@Getter
	@Setter
	public static class Add extends CiamsPlanContent {
		private List<MultipartFile> file;

		private List<CiamsPlanContentDetail> detailList;
	}

	@Getter
	@Setter
	public static class AddDetail extends CiamsPlanContent {
		private List<CiamsPlanContentDetailDto.AddParam> detailList;
	}

	@Getter
	@Setter
	public static class Search{
		String planId;
		String planContentLinkId;
		String planContentId;
		String planAreaId;
		String category;
	}

	@Getter
	@Setter
	public static class Modify extends CiamsPlanContent {
		List<CiamsPlanContentDetailDto.ModifyParam> detailList;
		private List<String> delList;
	}

	@Getter
	@Setter
	public static class Priority{
		String planContentLinkId;
		String planContentId;
		int sortSn;
	}

	@Getter
	@Setter
	public static class Delete{
		String planContentId;
		String planContentDetailId;
	}
}
