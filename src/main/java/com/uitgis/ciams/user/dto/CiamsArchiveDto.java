package com.uitgis.ciams.user.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uitgis.ciams.model.CiamsArchive;
import com.uitgis.ciams.model.CiamsFile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CiamsArchiveDto {

	@Getter
	@Setter
	public static class Find extends PaginationDto{
		private String categoryId;
		private String typeCode;
		private String title;
		private Boolean hidden;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Add{

		@Builder
		public Add(String title, String contents) {
			this.title = title;
			this.contents = contents;
		}

		private String archiveId;
		private String title;
		private String categoryId;
		private String contents;
		private String regUser;

		private MultipartFile imgFile;				//대표이미지
		private List<MultipartFile> attachFiles;	//첨부파일
		private List<String> imageFileIds;
	}

	@Getter
	@Setter
	public static class Modify extends CiamsArchive{

		private List<String> removeFilesIds;		//삭제대상 파일ID
		private MultipartFile imgFile;				//대표이미지
		private List<MultipartFile> attachFiles;	//첨부파일
		private List<String> imageFileIds;
	}

	@Getter
	@Setter
	public static class ModifyAll {
		private List<String> ids;
		private Boolean hidden;
		private boolean remove;
		private String username;
	}

	@Getter
	@Setter
	public static class WithFile extends CiamsArchive {
//		private MultipartFile imgFile;				//대표이미지
		List<CiamsFile> archiveFiles;
	}

	@Getter
	@Setter
	public static class ListResult extends CiamsArchive {
		@JsonIgnore
		public String getContents() {
			return super.getContents();
		}

		private List<CiamsFile> archiveFiles;
	}
}
