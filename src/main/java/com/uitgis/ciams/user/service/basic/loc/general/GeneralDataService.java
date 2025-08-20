package com.uitgis.ciams.user.service.basic.loc.general;

import com.uitgis.ciams.user.dto.basic.loc.general.GeneralDataDto;

import java.util.List;

public interface GeneralDataService {
	/**
	 * 일반현황 데이터 Item 목록
	 */
	List<GeneralDataDto.DataItem> getGeneralDataItems();


	/**
	 * 데이터 그룹 정보
	 *
	 * @param itemId 데이터 Item ID
	 * @return
	 */
	GeneralDataDto.DataGroupInfo getDataGroupInfo(int itemId);


	/**
	 * 데이터 상세 정보
	 *
	 * @param dataId 데이터 ID
	 * @return
	 */
	GeneralDataDto.DataDetailInfo getDataDetailInfo(int dataId);
}
