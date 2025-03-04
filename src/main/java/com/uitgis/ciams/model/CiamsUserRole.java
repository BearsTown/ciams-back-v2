package com.uitgis.ciams.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsUserRole {
	String userId;
	String userNm;
	String userRole;
	String deptNm;
	String roleYn;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}
