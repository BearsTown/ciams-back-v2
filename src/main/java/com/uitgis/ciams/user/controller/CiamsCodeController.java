package com.uitgis.ciams.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.user.dto.CiamsCodeDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.service.CiamsCodeService;
import com.uitgis.ciams.service.CiamsCommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/codes")
public class CiamsCodeController {
    private final CiamsCodeService ciamsCodeService;
    private final CiamsCommonService ciamsCommonService;


    @GetMapping("{code}")
    public ResponseEntity<?> getCode(@PathVariable String code) {
        CiamsCodeDto.Find result = ciamsCodeService.selectCodeByCode(code);
        return ResponseEntity.ok(result);
    }


    /**
     * 하위 코드 목록 조회.
     *
     * @param parentCode 상위 코드 아이디
     * @return 하위 코드 목록
     */
    @GetMapping("{parentCode}/child")
    public ResponseEntity<?> list(@PathVariable String parentCode) {

        List<CiamsCodeDto.Find> result = null;
        //root코드 하위 목록
        if (parentCode.equals("0")) {
            result = ciamsCodeService.selectCodeListByParentCode(null);
        } else {
            result = ciamsCodeService.selectCodeListByParentCode(parentCode);
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<?> addCode(@RequestBody CiamsCodeDto.Add add) throws Exception {
        ciamsCodeService.addCode(add);

        CiamsCodeDto.Find ciamsCode = ciamsCodeService.selectCodeByCode(add.getCode());

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.ADD, ciamsCode.getCode());

        return ResponseEntity.ok(ciamsCode);
    }


    @PutMapping
    public ResponseEntity<?> modify(@RequestBody CiamsCodeDto.Modify mod) throws Exception {
        ciamsCodeService.modify(mod);

        CiamsCodeDto.Find ciamsCode = ciamsCodeService.selectCodeByCode(mod.getCode());

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.UPDATE, ciamsCode.getCode());

        return ResponseEntity.ok(ciamsCode);
    }


    @PutMapping("priority")
    public ResponseEntity<?> changeCodePriority(@RequestBody List<CiamsCodeDto.Modify> mod) throws Exception {

        List<CiamsCode> codeList = mod.stream()
                .map(obj -> {
                    CiamsCode param = new CiamsCode();
                    param.setCode(obj.getCode());
                    param.setSortSn(obj.getSortSn());
                    return param;
                })
                .collect(Collectors.toList());

        ciamsCodeService.changeCodePriority(codeList);

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.UPDATE, mod.stream().map(a-> a.getCode()).collect(Collectors.toList()));

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{code}")
    public ResponseEntity<?> remove(@PathVariable String code) throws Exception {

        ciamsCodeService.removeByCode(code);

        ciamsCommonService.log(MenuEnum.CODE, ActionTypeEnum.DELETE, code);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/sub")
    public ResponseEntity<?> selectCodeSublist(CiamsCodeDto.Sub sub) {
        List<CiamsCode> list = ciamsCodeService.selectCodeSublist(sub);
        return ResponseEntity.ok(list);
    }

}
