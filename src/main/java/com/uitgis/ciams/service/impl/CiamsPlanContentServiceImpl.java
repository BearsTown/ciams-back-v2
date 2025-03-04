package com.uitgis.ciams.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uitgis.ciams.dto.CiamsPlanContentDetailDto;
import com.uitgis.ciams.dto.CiamsPlanContentDto;
import com.uitgis.ciams.dto.CiamsPlanContentDto.Modify;
import com.uitgis.ciams.dto.CiamsPlanContentDto.Search;
import com.uitgis.ciams.dto.CiamsPlanContentDto.SelectDetail;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.mapper.CiamsPlanContentDetailMapper;
import com.uitgis.ciams.mapper.CiamsPlanContentLinkMapper;
import com.uitgis.ciams.mapper.CiamsPlanContentMapper;
import com.uitgis.ciams.model.CiamsPlanContent;
import com.uitgis.ciams.model.CiamsPlanContentDetail;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.service.CiamsPlanContentService;
import com.uitgis.ciams.util.ValidUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsPlanContentServiceImpl implements CiamsPlanContentService {

	private final CiamsPlanContentLinkMapper ciamsPlanContentLinkMapper;

	private final CiamsPlanContentMapper ciamsPlanContentMapper;

	private final CiamsPlanContentDetailMapper ciamsPlanContentDetailMapper;

	private final CiamsFileService ciamsFileService;
	
	private final CiamsCommonService ciamsCommonService;

	@Transactional
	@Override
	public int insert(CiamsPlanContentDto.AddDetail params) throws Exception {
		int cnt = 0;
		String id = UUID.randomUUID().toString();
		params.setPlanContentId(id);
		cnt = ciamsPlanContentMapper.insert(params);

		for(CiamsPlanContentDetailDto.AddParam detail : params.getDetailList()) {
			String detailId = UUID.randomUUID().toString();
			detail.setPlanContentId(id);
			detail.setPlanContentDetailId(detailId);
			ciamsPlanContentDetailMapper.insert(detail);
			
			if(ValidUtil.notEmpty(detail.getNewFileList()) && detail.getNewFileList().size() > 0) {
				ciamsFileService.uploadFiles(detail.getNewFileList(), "content", detailId);
			}
		}

		ciamsCommonService.log(MenuEnum.CONTENT, ActionTypeEnum.ADD, id);
		
		return cnt;
	}

	@Transactional
	@Override
	public int update(Modify params) throws Exception {
		int cnt = 0;
		CiamsPlanContent content = new CiamsPlanContent();
		BeanUtils.copyProperties(params, content);
		cnt = ciamsPlanContentMapper.update(content);

		for(CiamsPlanContentDetailDto.ModifyParam detail : params.getDetailList()) {

			if(ValidUtil.notEmpty(detail.getPlanContentDetailId())) {
				//상세수정
				ciamsPlanContentDetailMapper.update(detail);
				
				//파일삭제
				if(ValidUtil.notEmpty(detail.getDelFileList()) && detail.getDelFileList().size() > 0) {
					ciamsFileService.deleteFilesByIds(detail.getDelFileList());
				}
				
				//파일(신규)등록
				if(ValidUtil.notEmpty(detail.getNewFileList()) && detail.getNewFileList().size() > 0) {
					ciamsFileService.uploadFiles(detail.getNewFileList(), "content", detail.getPlanContentDetailId());
				}
				
				//첨부파일 sortSn 재정렬
				ciamsFileService.updateFilesSortSn(detail.getPlanContentDetailId());
				
			}else {
				//상세(신규)등록
				String detailId = UUID.randomUUID().toString();
				detail.setPlanContentDetailId(detailId);
				detail.setPlanContentId(content.getPlanContentId());
				ciamsPlanContentDetailMapper.insert(detail);
				
				//파일삭제
				if(ValidUtil.notEmpty(detail.getDelFileList()) && detail.getDelFileList().size() > 0) {
					ciamsFileService.deleteFilesByIds(detail.getDelFileList());
				}
				
				//파일(신규)등록
				if(ValidUtil.notEmpty(detail.getNewFileList()) && detail.getNewFileList().size() > 0) {
					ciamsFileService.uploadFiles(detail.getNewFileList(), "content", detailId);
				}
				
			}
		};

		if(ValidUtil.notEmpty(params.getDelList()) && params.getDelList().size() > 0) {
			//파일삭제
			ciamsFileService.deleteFilesByLinkIds(params.getDelList());
			
			CiamsPlanContentDetailDto.DeleteParam param = new CiamsPlanContentDetailDto.DeleteParam();
			params.getDelList().forEach(id->{
				param.setPlanContentDetailId(id);
				//상세삭제
				ciamsPlanContentDetailMapper.delete(param);
			});
		}
		
		//용도종류 컨텐츠 인 경우 > 파일이 존재하지 않음 (삭제처리)
		if(params.getIsType()) {
			List<String> ids = params.getDetailList().stream().map(a->a.getPlanContentDetailId()).collect(Collectors.toList());
			//파일삭제
			ciamsFileService.deleteFilesByLinkIds(ids);
		}
		
		ciamsCommonService.log(MenuEnum.CONTENT, ActionTypeEnum.UPDATE, params.getPlanContentId());

		return cnt;
	}

	@Override
	public int delete(String type, String id) throws Exception {
		int cnt = 0;

		//링크 삭제
		if("all".equals(type)) {
			ciamsPlanContentLinkMapper.deleteByContentId(id);
		}
		
		//컨텐츠 상세 파일 삭제
		CiamsPlanContentDetail params = new CiamsPlanContentDetail();
		params.setPlanContentId(id);
		List<CiamsPlanContentDetail> selectList = ciamsPlanContentDetailMapper.select(params);
		if(!ValidUtil.empty(selectList)) {
			List<String> ids = selectList.stream().map(a->a.getPlanContentDetailId()).collect(Collectors.toList());
			//파일삭제
			ciamsFileService.deleteFilesByLinkIds(ids);
		}
		
		//컨텐츠 상세 삭제
		CiamsPlanContentDetailDto.DeleteParam parma = new CiamsPlanContentDetailDto.DeleteParam();
		parma.setPlanContentId(id);
		ciamsPlanContentDetailMapper.delete(parma);

		//컨텐츠 삭제
		cnt = ciamsPlanContentMapper.deleteById(id);
		
		ciamsCommonService.log(MenuEnum.CONTENT, ActionTypeEnum.DELETE, id);

		return cnt;
	}

	@Override
	public List<SelectDetail> selectList(Search params) {
		return ciamsPlanContentMapper.selectList(params);
	}

	@Override
	public int changePriority(String type, List<CiamsPlanContentDto.Priority> list) {
		int cnt = 0;
		if("planContent".equals(type)) {
			CiamsPlanContent model = new CiamsPlanContent();
			for (CiamsPlanContentDto.Priority obj : list) {
				model.setPlanContentId(obj.getPlanContentId());
//				model.setSortSn(obj.getSortSn());
				cnt += ciamsPlanContentMapper.update(model);
			}
		}else {
			CiamsPlanContentDetail model = new CiamsPlanContentDetail();
			for (CiamsPlanContentDto.Priority obj : list) {
				model.setPlanContentId(obj.getPlanContentId());
				model.setSortSn(obj.getSortSn());
				cnt += ciamsPlanContentDetailMapper.update(model);
			}
		}
		
		ciamsCommonService.log(MenuEnum.CONTENT, ActionTypeEnum.UPDATE, list.stream().map(a-> a.getPlanContentLinkId()).collect(Collectors.toList()));

		return cnt;
	}

	@Override
	public List<SelectDetail> selectAreaList(Search params) {
		return ciamsPlanContentMapper.selectAreaList(params);
	}

}
