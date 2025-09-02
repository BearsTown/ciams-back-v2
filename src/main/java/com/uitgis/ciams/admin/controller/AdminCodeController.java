package com.uitgis.ciams.admin.controller;

import com.uitgis.ciams.admin.dto.CodeDto;
import com.uitgis.ciams.admin.service.AdminCodeService;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.service.CiamsCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/codes")
public class AdminCodeController {
    private final AdminCodeService adminCodeService;
    private final CiamsCommonService ciamsCommonService;


    @GetMapping("/{code}")
    public ResponseEntity<?> getCode(@PathVariable String code) {
        CodeDto.Find result = adminCodeService.selectCodeByCode(code);
        return ResponseEntity.ok(result);
    }


    /**
     * 하위 코드 목록 조회.
     *
     * @param parentCode 상위 코드 아이디
     * @return 하위 코드 목록
     */
    @GetMapping("/{parentCode}/child")
    public ResponseEntity<?> list(@PathVariable String parentCode) {
        List<CodeDto.Find> result = null;
        //root코드 하위 목록
        if (parentCode.equals("0")) {
            result = adminCodeService.selectCodeListByParentCode(null);
        } else {
            result = adminCodeService.selectCodeListByParentCode(parentCode);
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<?> addCode(@RequestBody CodeDto.Add add) throws Exception {
        adminCodeService.addCode(add);

        CodeDto.Find ciamsCode = adminCodeService.selectCodeByCode(add.getCode());

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.ADD, ciamsCode.getCode());

        return ResponseEntity.ok(ciamsCode);
    }


    @PutMapping
    public ResponseEntity<?> modify(@RequestBody CodeDto.Modify mod) throws Exception {
        adminCodeService.modify(mod);

        CodeDto.Find ciamsCode = adminCodeService.selectCodeByCode(mod.getCode());

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.UPDATE, ciamsCode.getCode());

        return ResponseEntity.ok(ciamsCode);
    }


    @PutMapping("/priority")
    public ResponseEntity<?> changeCodePriority(@RequestBody List<CodeDto.Modify> mod) throws Exception {
        List<CiamsCode> codeList = mod.stream()
                .map(obj -> {
                    CiamsCode param = new CiamsCode();
                    param.setCode(obj.getCode());
                    param.setSortSn(obj.getSortSn());
                    return param;
                })
                .collect(Collectors.toList());

        adminCodeService.changeCodePriority(codeList);

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.UPDATE, mod.stream().map(a-> a.getCode()).collect(Collectors.toList()));

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{code}")
    public ResponseEntity<?> remove(@PathVariable String code) throws Exception {
        adminCodeService.removeByCode(code);

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.DELETE, code);

        return ResponseEntity.ok().build();
    }

}
