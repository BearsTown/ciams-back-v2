package com.uitgis.ciams.admin.controller;

import com.uitgis.ciams.admin.dto.AccessDto;
import com.uitgis.ciams.admin.service.AdminAccessService;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.service.CiamsCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/access")
public class AdminAccessController {
    private final AdminAccessService adminAccessService;
    private final CiamsCommonService ciamsCommonService;


    /**
     * 목록 조회.
     *
     * @param parentId 상위 코드 아이디
     * @return 하위 코드 목록
     */
    @GetMapping("/{type}")
    public ResponseEntity<?> list(@PathVariable String type, AccessDto params) {
        if ("user".equals(type)) {

            List<AccessDto.UserResult> result = adminAccessService.getRoleByUserList(params);

            return ResponseEntity.ok(result);
        } else {

            List<AccessDto.RoleResult> result = adminAccessService.getRoleByMenuList(params);

            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/{type}")
    public ResponseEntity<?> addMenu(@PathVariable String type, @RequestBody AccessDto.Add params) throws Exception {
        AccessDto selectParams = new AccessDto();
        BeanUtils.copyProperties(params, selectParams);

        if ("user".equals(type)) {
            adminAccessService.addRoleByUserCode(params);

            List<AccessDto.UserResult> result = adminAccessService.getRoleByUserList(selectParams);

            ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.ADD, params.getRoleCode());
            return ResponseEntity.ok(result);
        } else {
            adminAccessService.addRoleByMenuCode(params);

            List<AccessDto.RoleResult> result = adminAccessService.getRoleByMenuList(selectParams);

            ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.ADD, params.getRoleCode());
            return ResponseEntity.ok(result);
        }

    }

    @PutMapping("/{type}")
    public ResponseEntity<?> delete(@PathVariable String type, @RequestBody AccessDto.Delete params) throws Exception {
        AccessDto selectParams = new AccessDto();
        BeanUtils.copyProperties(params, selectParams);

        if ("user".equals(type)) {
            adminAccessService.removeRoleByUserCode(params);

            List<AccessDto.UserResult> result = adminAccessService.getRoleByUserList(selectParams);

            ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.DELETE, params.getRoleCode());
            return ResponseEntity.ok(result);
        } else {
            adminAccessService.removeRoleByMenuCode(params);

            List<AccessDto.RoleResult> result = adminAccessService.getRoleByMenuList(selectParams);

            ciamsCommonService.log(MenuEnum.ACCESS, ActionTypeEnum.DELETE, params.getRoleCode());
            return ResponseEntity.ok(result);
        }
    }

}
