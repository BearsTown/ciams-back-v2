package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsUserService;
import com.uitgis.ciams.user.dto.CiamsUserDto;
import com.uitgis.ciams.user.dto.CiamsUserDto.Select;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class CiamsUserController {
	private final CiamsCommonService ciamsCommonService;
	private final CiamsUserService ciamsUserService;


	@PostMapping("/signUp")
	public ResponseEntity<?> addUser(@RequestBody CiamsUserDto.Add dto) throws Exception {

		try {
			ciamsUserService.addUser(dto);

			ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.ADD, dto.getId());
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>("로그인ID중복입니다.", HttpStatus.CONFLICT);
		}

		return ResponseEntity.ok().build();
	}


	@GetMapping("/info/session")
	public ResponseEntity<?> sessionUser() throws Exception {
		Select userDto = ciamsUserService.selectById(ciamsCommonService.getUsername());
		return ResponseEntity.ok(userDto);
	}


	@GetMapping("/{loginId}")
	public ResponseEntity<?> selectUser(@PathVariable String loginId) throws Exception {
		Select userDto = ciamsUserService.selectById(loginId);
		return ResponseEntity.ok(userDto);
	}


	/**
	 * 로그인 사용자의 정보 수정
	 *
	 * @param param 수정 정보
	 */
	@PutMapping("/{loginId}")
	public ResponseEntity<?> modify(@PathVariable String loginId, @RequestBody CiamsUserDto.Modify param) throws Exception {

		param.setLoginId(loginId);
		ciamsUserService.modify(param);

		ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, loginId);

		return ResponseEntity.ok().build();
	}
}
