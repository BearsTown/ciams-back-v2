package com.uitgis.ciams.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.model.CiamsPlanContentDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsPlanContentDetailDto {

	@Getter
	@Setter
	public static class DeleteParam{
		// 성장관리계획 운영 컨텐츠 디테일_고유번호
		String planContentDetailId;
		// 성장관리계획 운영 컨텐츠_고유번호
		String planContentId;
	}

	@Getter
	@Setter
	public static class AddParam extends CiamsPlanContentDetail{
		List<MultipartFile> newFileList;
		// 성장관리계획 운영 컨텐츠_고유번호
	}

	@Getter
	@Setter
	public static class ModifyParam extends CiamsPlanContentDetail{
		List<MultipartFile> newFileList;
		List<String> delFileList;
	}

	@Getter
	@Setter
	public static class Detail extends CiamsPlanContentDetail{
		List<CiamsFile> fileList;
		// 성장관리계획 운영 컨텐츠_고유번호
	}
}
