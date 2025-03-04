package com.uitgis.ciams.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.dto.CiamsAccessDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.service.CiamsAccessService;
import com.uitgis.ciams.service.CiamsCommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/access")
public class CiamsAccessController {

    private final CiamsAccessService ciamsAccessService;
    private final CiamsCommonService ciamsCommonService;

    /**
     * 메뉴 접근 목록 조회.
     *
     * @return 하위 코드 목록
     * @throws Exception
     */
    @GetMapping("menu/check")
    public ResponseEntity<?> menuAcccessList() throws Exception {

		List<CiamsAccessDto.MenuAccess> result = ciamsAccessService.getMenuAccessList();

		return ResponseEntity.ok(result);
    }

    /**
     * 목록 조회.
     *
     * @param parentId 상위 코드 아이디
     * @return 하위 코드 목록
     */
    @GetMapping("{type}")
    public ResponseEntity<?> list(@PathVariable String type, CiamsAccessDto params) {


    	if("user".equals(type)) {

    		List<CiamsAccessDto.UserResult> result = ciamsAccessService.getRoleByUserList(params);

    		return ResponseEntity.ok(result);
    	}else {

    		List<CiamsAccessDto.RoleResult> result = ciamsAccessService.getRoleByMenuList(params);

    		return ResponseEntity.ok(result);
    	}
    }

    @PostMapping("{type}")
    public ResponseEntity<?> addMenu(@PathVariable String type, @RequestBody CiamsAccessDto.Add params) throws Exception {

    	CiamsAccessDto selectParams = new CiamsAccessDto();
    	BeanUtils.copyProperties(params, selectParams);
    	if("user".equals(type)) {
    		ciamsAccessService.addRoleByUserCode(params);

    		List<CiamsAccessDto.UserResult> result = ciamsAccessService.getRoleByUserList(selectParams);

    		ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.ADD, params.getRoleCode());
    		return ResponseEntity.ok(result);
    	}else {
    		ciamsAccessService.addRoleByMenuCode(params);

    		List<CiamsAccessDto.RoleResult> result = ciamsAccessService.getRoleByMenuList(selectParams);

    		ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.ADD, params.getRoleCode());
    		return ResponseEntity.ok(result);
    	}

    }

    @PutMapping("{type}")
    public ResponseEntity<?> delete(@PathVariable String type, @RequestBody CiamsAccessDto.Delete params) throws Exception {

    	CiamsAccessDto selectParams = new CiamsAccessDto();
    	BeanUtils.copyProperties(params, selectParams);
    	if("user".equals(type)) {
    		ciamsAccessService.removeRoleByUserCode(params);

    		List<CiamsAccessDto.UserResult> result = ciamsAccessService.getRoleByUserList(selectParams);

    		ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.DELETE, params.getRoleCode());
    		return ResponseEntity.ok(result);
    	}else {
    		ciamsAccessService.removeRoleByMenuCode(params);

    		List<CiamsAccessDto.RoleResult> result = ciamsAccessService.getRoleByMenuList(selectParams);

    		ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.DELETE, params.getRoleCode());
    		return ResponseEntity.ok(result);
    	}

    }

}
