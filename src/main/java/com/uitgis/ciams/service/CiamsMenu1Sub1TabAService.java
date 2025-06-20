package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu1Sub1TabADto;

import java.util.List;

public interface CiamsMenu1Sub1TabAService {
	public List<CiamsMenu1Sub1TabADto.CiamsStatus> getStatusTree();

	public List<CiamsMenu1Sub1TabADto.StatusGroup> getStatusGroups(int statusId);

	public CiamsMenu1Sub1TabADto.DataInfo getDataInfo(int dataId);
}
