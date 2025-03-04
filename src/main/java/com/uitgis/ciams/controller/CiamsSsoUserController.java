package com.uitgis.ciams.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.dto.CiamsUserRoleDto;
import com.uitgis.ciams.dto.CiamsSsoUserDto;
import com.uitgis.ciams.dto.CiamsSsoUserDto.Select;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.service.CiamsUserRoleSerice;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsSsoUserService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class CiamsSsoUserController {

	private final CiamsSsoUserService ciamsSsoUserService;
	private final CiamsUserRoleSerice ciamsUserRoleSerice;
	private final CiamsCommonService ciamsCommonService;

	@GetMapping("/info/session")
	public ResponseEntity<?> sessionUser() throws Exception{
		Select userDto = ciamsSsoUserService.selectById(ciamsCommonService.getUsername());
		return ResponseEntity.ok(userDto);
	}

	@GetMapping("/list")
	public ResponseEntity<?> list(CiamsSsoUserDto.Find find) throws Exception{
		Map<String, Object> list = ciamsSsoUserService.selectList(find);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{loginId}")
	public ResponseEntity<?> selectUser(@PathVariable String loginId) throws Exception{
		Select userDto = ciamsSsoUserService.selectById(loginId);
		return ResponseEntity.ok(userDto);
	}



	/**
	 * 사용자 패스워드 초기화
	 *
	 * @param loginId 초기화 대상 사용자 아이디
	 */
	@PutMapping("{loginId}/initPass")
	public ResponseEntity<?> initPassword(@PathVariable String loginId) throws Exception {

		ciamsSsoUserService.initPass(loginId);

		ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, loginId);

		return ResponseEntity.ok().build();
	}

	/**
	 * 로그인 사용자의 정보 수정
	 *
	 * @param param 수정 정보
	 * @throws BsInvalidParameterException 파라미터 오류
	 * @throws BsDataNotFoundException     정보 수정 대상 사용자 정보 없음
	 */
	@PutMapping("{loginId}")
	public ResponseEntity<?> modify(@PathVariable String loginId, @RequestBody CiamsSsoUserDto.Modify param) throws Exception {

		param.setLoginId(loginId);
		ciamsSsoUserService.modify(param);

		ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, loginId);

		return ResponseEntity.ok().build();
	}

	/**
	 * 삭제
	 *
	 * @param userList 삭제 대상 사용자 아이디
	 */
	@DeleteMapping
	public ResponseEntity<?> remove(@RequestParam("userList") List<String> userList) throws Exception {

		ciamsSsoUserService.removeByIds(userList);

		ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.DELETE, userList);

		return ResponseEntity.ok().build();
	}

	/**
	 * 승인
	 *
	 * @param param
	 */
	@PutMapping("approve")
	public ResponseEntity<?> approve(@RequestBody CiamsUserRoleDto.Approve approve) throws Exception {

		ciamsUserRoleSerice.modifyApprove(approve);

		ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, approve.getUserList());

		return ResponseEntity.ok().build();
	}

	/**
	 * 잠김
	 *
	 * @param param
	 */
	@PutMapping("lock")
	public ResponseEntity<?> lock(@RequestBody CiamsSsoUserDto.Lock lockUsers) throws Exception {

		ciamsSsoUserService.updateLockUsers(lockUsers);

		ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, lockUsers.getUserList());

		return ResponseEntity.ok().build();
	}
}
