package com.uitgis.ciams.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsBdEtcDto {
	public static class Search {
		@Getter
		@Setter
		public static class param {
			private String pnu;
		}

		@Getter
		@Setter
		public static class Row {
			private int isbinjib;
			private int iswiban;
		}
	}
}
