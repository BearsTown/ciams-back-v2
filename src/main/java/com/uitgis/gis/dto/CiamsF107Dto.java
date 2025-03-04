package com.uitgis.gis.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsF107Dto {
	
	@Getter
    @Setter
	public static class Search{
		private String planId;
		private List<String> emdCds;
		
    }
}	
