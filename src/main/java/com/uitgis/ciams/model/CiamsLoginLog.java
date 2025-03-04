package com.uitgis.ciams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiamsLoginLog {
	String loginId;
	String ip;
    private String loginDate;
}
