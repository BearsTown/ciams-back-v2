package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.Menu1Sub1TabADto;

import java.util.List;

public interface Menu1Sub1TabAService {
	public List<Menu1Sub1TabADto.CiamsStatus> getStatusTree();


//	public List<CiamsMenu1Sub1TabADto.StatusGroup> getStatusGroups(int statusId);
	public Menu1Sub1TabADto.CiamsStatusInfo getStatusInfo(int statusId);

	public Menu1Sub1TabADto.DataInfo getDataInfo(int dataId);
}
