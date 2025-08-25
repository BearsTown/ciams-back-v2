package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.config.UitPasswordEncoder;
import com.uitgis.ciams.model.CiamsAccessUserLink;
import com.uitgis.ciams.model.CiamsSsoUser;
import com.uitgis.ciams.model.CiamsUserRole;
import com.uitgis.ciams.service.CiamsSsoUserService;
import com.uitgis.ciams.user.dto.CiamsConfigDto;
import com.uitgis.ciams.user.dto.CiamsSsoUserDto;
import com.uitgis.ciams.user.dto.CiamsSsoUserDto.Lock;
import com.uitgis.ciams.user.dto.CiamsSsoUserDto.Modify;
import com.uitgis.ciams.user.dto.CiamsSsoUserDto.Select;
import com.uitgis.ciams.user.dto.CiamsUserRoleDto;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.user.mapper.CiamsAccessMapper;
import com.uitgis.ciams.user.mapper.CiamsConfigMapper;
import com.uitgis.ciams.user.mapper.CiamsSsoUserMapper;
import com.uitgis.ciams.user.mapper.CiamsUserRoleMapper;
import com.uitgis.ciams.util.CipherUtil;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsSsoUserServiceImpl implements CiamsSsoUserService {

	private final CiamsAccessMapper ciamsAccessMapper;
	private final CiamsConfigMapper ciamsConfigMapper;
	private final CiamsSsoUserMapper ciamsSsoUserMapper;
	private final CiamsUserRoleMapper ciamsUserRoleMapper;
	private final PasswordEncoder passwordEncoder;
//	private final UitPasswordEncoder uitPasswordEncoder;


	@Override
	public Map<String, Object> selectList(CiamsSsoUserDto.Find find) {

		int cnt = ciamsSsoUserMapper.selectCnt(find);

		PaginationDto page = PageUtil.setTotalCount(find, cnt);

		List<CiamsSsoUserDto.Data> list = ciamsSsoUserMapper.selectList(find);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("page", page);

		return resultMap;
	}

	@Override
	@Transactional
	public void addUser(CiamsSsoUserDto.Add dto) throws Exception {

		CiamsSsoUserDto.Find find = new CiamsSsoUserDto.Find();
		find.setPage(false);
		List<CiamsSsoUserDto.Data> userList = ciamsSsoUserMapper.selectList(find);

		boolean dupCheck = userList.stream().anyMatch(a->a.getLoginId().equals(dto.getId()));

		if(dupCheck) {
			throw new DuplicateKeyException("로그인ID중복");
		}else {
			String rsaEncPass = dto.getPassword();
			rsaEncPass = CipherUtil.decryptRSA(rsaEncPass);
			String encPass = passwordEncoder.encode(rsaEncPass);
			dto.setPassword(encPass);

			CiamsConfigDto.WithFile signUpConfig = ciamsConfigMapper.selectById("SIGNUP_AUTO");
			String roleYn = (signUpConfig != null && signUpConfig.getUsed()) ? "Y" : "N";

			ciamsSsoUserMapper.insertUser(dto);

			CiamsUserRoleDto.Add roleDto = CiamsUserRoleDto.Add.builder()
											.userId(dto.getId())
											.userNm(dto.getName())
											.userRole("R0001")
					.roleYn(roleYn)
											.build();

			ciamsUserRoleMapper.insert(roleDto);


//			if (roleYn.equals("Y")) {
			CiamsAccessUserLink userLink = CiamsAccessUserLink.builder()
					.accessRoleCode("R0190")
					.userId(dto.getId())
					.regUser(dto.getName())
					.build();
			ciamsAccessMapper.insertUser(userLink);
//			}
		}
	}

	/**
	 * 패스워드 초기화
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void initPass(String loginId) {

		CiamsSsoUser user = new CiamsSsoUser();
		user.setLoginId(loginId);
		user.setUserPassword(passwordEncoder.encode(loginId));
		user.setLoginFailCnt(0);

		ciamsSsoUserMapper.updateById(user);
	}

	/**
	 * 사용자 삭제
	 * @param userList
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public void removeByIds(List<String> userList) {

		ciamsSsoUserMapper.deleteByIds(userList);
		ciamsUserRoleMapper.deleteByIds(userList);
	}

	@Override
	public void updateLockUsers(Lock lockUsers) {
		ciamsSsoUserMapper.updateLockUsers(lockUsers);
	}

	@Override
	@Transactional
	public void modify(Modify param) {

		CiamsSsoUser modify = new CiamsSsoUser();
		BeanUtils.copyProperties(param, modify);

		String newPassword = param.getNewPassword();
		if (ValidUtil.notEmpty(newPassword)) {

			CiamsSsoUserDto.Data model = ciamsSsoUserMapper.selectById(param.getLoginId());
			String curPassword ="";
			try {
				curPassword = CipherUtil.decryptRSA(param.getCurPassword());
			} catch (NoSuchAlgorithmException | NoSuchPaddingException |
					InvalidKeyException | IllegalBlockSizeException| BadPaddingException e) {
				log.error(e.getMessage());
			}

			if (!passwordEncoder.matches(curPassword, model.getUserPassword())) {
				throw new BadCredentialsException("Invalid password");
			}
//			modify.setUserPassword(passwordEncoder.encode(param.getNewPassword()));
			modify.setUserPassword(uitPasswordEncoder().encode(param.getNewPassword()));
			ciamsSsoUserMapper.updateById(modify);
		}else {
			ciamsSsoUserMapper.updateById(modify);

			CiamsUserRole modifyRole = new CiamsUserRole();
			modifyRole.setUserId(param.getLoginId());
			modifyRole.setUserRole(param.getUserRole());

			CiamsUserRole rol = ciamsUserRoleMapper.selectById(param.getLoginId());
			if(rol != null) {
				ciamsUserRoleMapper.updateById(modifyRole);
			}else {
				modifyRole.setRoleYn("Y");

				CiamsUserRoleDto.Add add = CiamsUserRoleDto.Add.builder().build();
				BeanUtils.copyProperties(modifyRole, add);
				ciamsUserRoleMapper.insert(add);
			}
		}
	}

	@Override
	public Select selectById(String loginId) {
		CiamsSsoUserDto.Select result = new CiamsSsoUserDto.Select();
		CiamsSsoUserDto.Data data = ciamsSsoUserMapper.selectById(loginId);
		BeanUtils.copyProperties(data, result);
		return result;
	}

	public PasswordEncoder uitPasswordEncoder() {
		return new UitPasswordEncoder();
	}
}
