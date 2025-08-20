package com.uitgis.ciams.user.dto;

import org.springframework.web.multipart.MultipartFile;

import com.uitgis.ciams.model.CiamsConfig;
import com.uitgis.ciams.model.CiamsFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CiamsConfigDto {
	@Getter
	@Setter
	@ToString
	public static class Find {
		private String id;
		private String confName;
		private String confType;
		private Boolean used;
	}

	@Getter
	@Setter
	public static class Add extends CiamsConfig{
		private MultipartFile attachFile;
	}

	@Getter
	@Setter
	public static class Modify extends CiamsConfig{
		private MultipartFile attachFile;	//신규 첨부파일
		private String removeFilesId;		//삭제대상 파일ID
	}

	@Getter
	@Setter
	public static class WithFile extends CiamsConfig {
		CiamsFile attachFile;
	}
}
