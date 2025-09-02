package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.admin.dto.UserRoleDto;
import com.uitgis.ciams.admin.mapper.AdminAccessMapper;
import com.uitgis.ciams.admin.mapper.AdminUserRoleMapper;
import com.uitgis.ciams.config.UitPasswordEncoder;
import com.uitgis.ciams.model.CiamsAccessUserLink;
import com.uitgis.ciams.model.CiamsUser;
import com.uitgis.ciams.model.CiamsUserRole;
import com.uitgis.ciams.service.CiamsUserService;
import com.uitgis.ciams.user.dto.CiamsConfigDto;
import com.uitgis.ciams.user.dto.CiamsUserDto;
import com.uitgis.ciams.user.dto.CiamsUserDto.Modify;
import com.uitgis.ciams.user.dto.CiamsUserDto.Select;
import com.uitgis.ciams.user.mapper.CiamsConfigMapper;
import com.uitgis.ciams.user.mapper.CiamsUserMapper;
import com.uitgis.ciams.util.CipherUtil;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsUserServiceImpl implements CiamsUserService {
	private final AdminAccessMapper adminAccessMapper;
	private final AdminUserRoleMapper adminUserRoleMapper;
	private final CiamsConfigMapper ciamsConfigMapper;
	private final CiamsUserMapper ciamsUserMapper;
	private final PasswordEncoder passwordEncoder;


	@Override
	@Transactional
	public void addUser(CiamsUserDto.Add dto) throws Exception {
		CiamsUserDto.Find find = new CiamsUserDto.Find();
		find.setPage(false);
		List<CiamsUserDto.Data> userList = ciamsUserMapper.selectList(find);

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

			ciamsUserMapper.insertUser(dto);

			UserRoleDto.Add roleDto = UserRoleDto.Add.builder()
											.userId(dto.getId())
											.userNm(dto.getName())
											.userRole("R0001")
					.roleYn(roleYn)
											.build();

			adminUserRoleMapper.insert(roleDto);

//			if (roleYn.equals("Y")) {
			CiamsAccessUserLink userLink = CiamsAccessUserLink.builder()
					.accessRoleCode("R0190")
					.userId(dto.getId())
					.regUser(dto.getName())
					.build();
			adminAccessMapper.insertUser(userLink);
//			}
		}
	}


	@Override
	@Transactional
	public void modify(Modify param) {
		CiamsUser modify = new CiamsUser();
		BeanUtils.copyProperties(param, modify);

		String newPassword = param.getNewPassword();
		if (ValidUtil.notEmpty(newPassword)) {

			CiamsUserDto.Data model = ciamsUserMapper.selectById(param.getLoginId());
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
			ciamsUserMapper.updateById(modify);
		}else {
			ciamsUserMapper.updateById(modify);

			CiamsUserRole modifyRole = new CiamsUserRole();
			modifyRole.setUserId(param.getLoginId());
			modifyRole.setUserRole(param.getUserRole());

			CiamsUserRole rol = adminUserRoleMapper.selectById(param.getLoginId());
			if(rol != null) {
				adminUserRoleMapper.updateById(modifyRole);
			}else {
				modifyRole.setRoleYn("Y");

				UserRoleDto.Add add = UserRoleDto.Add.builder().build();
				BeanUtils.copyProperties(modifyRole, add);
				adminUserRoleMapper.insert(add);
			}
		}
	}


	@Override
	public Select selectById(String loginId) {
		CiamsUserDto.Select result = new CiamsUserDto.Select();
		CiamsUserDto.Data data = ciamsUserMapper.selectById(loginId);
		BeanUtils.copyProperties(data, result);
		return result;
	}

	public PasswordEncoder uitPasswordEncoder() {
		return new UitPasswordEncoder();
	}
}
