package com.uitgis.ciams.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uitgis.gis.mapper.GisMapper;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto.Search.Row;
import com.uitgis.ciams.dto.CiamsPlanContentLinkDto;
import com.uitgis.ciams.dto.CiamsPlanContentLinkDto.Add;
import com.uitgis.ciams.dto.CiamsPlanContentLinkDto.Delete;
import com.uitgis.ciams.dto.CiamsPlanContentLinkDto.Select;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.mapper.CiamsPlanContentLinkMapper;
import com.uitgis.ciams.mapper.CiamsPlanContentMapper;
import com.uitgis.ciams.model.CiamsPlanContent;
import com.uitgis.ciams.model.CiamsPlanContentLink;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsPlanContentLinkService;
import com.uitgis.ciams.util.ValidUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsPlanContentLinkServiceImpl implements CiamsPlanContentLinkService {

	private final CiamsPlanContentLinkMapper ciamsPlanContentLinkMapper;
	private final CiamsPlanContentMapper ciamsPlanContentMapper;
	private final CiamsCommonService ciamsCommonService;
	private final GisMapper gisMapper;

	@Qualifier("ciamsSqlSessionFactory")
	private final SqlSessionFactory sqlSessionFactory;

	@Override
	public int insert(Add params) throws Exception {

//		if(ValidUtil.empty(params.getPlanContentLinkId())) {
//			params.setPlanContentLinkId(planContentLinkId);
//
//			CiamsPlanAreaLinkDto.Modify modify = new CiamsPlanAreaLinkDto.Modify();
//			BeanUtils.copyProperties(params, modify);
//			ciamsPlanAreaLinkMapper.update(modify);
//		}

		//기존 update
		CiamsPlanContentLinkDto _params = new CiamsPlanContentLinkDto();
        BeanUtils.copyProperties(params, _params);
		List<CiamsPlanContentLink> oldList = ciamsPlanContentLinkMapper.selectCategoryList(_params);

		int sortSn = 1;
		List<CiamsPlanContentLink> list = new ArrayList<CiamsPlanContentLink>();
		for (String planContentId : params.getPlanContentIds()) {
			CiamsPlanContentLink obj = new CiamsPlanContentLink();
			obj.setPlanContentLinkId(UUID.randomUUID().toString());
			obj.setPlanContentId(planContentId);
			obj.setSortSn(sortSn++);
			obj.setPlanId(params.getPlanId());
			obj.setPlanAreaId(params.getPlanAreaId());
			list.add(obj);
		};

		if(!ValidUtil.empty(oldList)) {
			for (CiamsPlanContentLink link : oldList) {

				CiamsPlanContentLinkDto.Modify modify = new CiamsPlanContentLinkDto.Modify();
				modify.setPlanContentLinkId(link.getPlanContentLinkId());
				modify.setSortSn(sortSn++);
				ciamsPlanContentLinkMapper.update(modify);
			}
		}

		int result = ciamsPlanContentLinkMapper.insertBatch(list);

		ciamsCommonService.log(MenuEnum.CONTENT_LINK, ActionTypeEnum.ADD, list.stream().map(a-> a.getPlanContentLinkId()).collect(Collectors.toList()));

		return result;
	}

	@Override
	public int update(List<CiamsPlanContentLinkDto.Modify> list) throws Exception {
		int cnt = 0;
		for (CiamsPlanContentLinkDto.Modify obj : list) {
			cnt += ciamsPlanContentLinkMapper.update(obj);
		}

		ciamsCommonService.log(MenuEnum.CONTENT_LINK, ActionTypeEnum.UPDATE, list.stream().map(a-> a.getPlanContentLinkId()).collect(Collectors.toList()));

		return cnt;
	}

	@Override
	public int delete(Delete params) throws Exception {

		int result = ciamsPlanContentLinkMapper.delete(params);

		ciamsCommonService.log(MenuEnum.CONTENT_LINK, ActionTypeEnum.DELETE, params.getPlanContentLinkId());

		return result;
	}

	@Override
	public List<CiamsPlanContentLink> selectList(Select params) {
		return ciamsPlanContentLinkMapper.selectList(params);
	}

	@Override
	public int insertArea(String planContentId) {

		CiamsPlanContentLinkDto.Select params = new Select();
		params.setPlanContentId(planContentId);

		List<CiamsPlanContentLink> linkList = ciamsPlanContentLinkMapper.selectList(params);

		List<Row> areaList = gisMapper.getPlanAreaList(null);

		CiamsPlanContent content = ciamsPlanContentMapper.select(planContentId);

		List<CiamsPlanContentLink> addLists = new ArrayList<CiamsPlanContentLink>();


		for(Row row : areaList) {
			boolean check = false;
			for(CiamsPlanContentLink link : linkList) {
//				if(row.getPlanId().equals(link.getPlanId()) && (row.getPlanAreaId()+"").equals(link.getPlanAreaId())) check = true;
			}
			if(!check) {
				CiamsPlanContentLink obj = new CiamsPlanContentLink();
				obj.setPlanContentLinkId(UUID.randomUUID().toString());
				obj.setPlanContentId(planContentId);
				obj.setSortSn(1);
//				obj.setPlanId(row.getPlanId());
//				obj.setPlanAreaId(row.getPlanAreaId()+"");
				addLists.add(obj);
			}
		}

		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			CiamsPlanContentLinkMapper mapper = sqlSession.getMapper(CiamsPlanContentLinkMapper.class);

			CiamsPlanContentLinkDto.Modify modify = new CiamsPlanContentLinkDto.Modify();
			modify.setCategory(content.getCategory());
			for(CiamsPlanContentLink link : addLists) {
				modify.setPlanAreaId(link.getPlanAreaId());
				mapper.updateSortSn(modify);
				mapper.insert(link);
			}

			sqlSession.flushStatements();
			sqlSession.commit();
		} finally {
			sqlSession.close();
			sqlSession.clearCache();
		}
		return 1;
	}

}
