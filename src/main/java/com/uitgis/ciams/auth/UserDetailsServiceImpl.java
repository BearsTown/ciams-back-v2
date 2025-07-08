package com.uitgis.ciams.auth;

import com.uitgis.ciams.dto.CiamsSsoUserDto;
import com.uitgis.ciams.enums.RoleEnum;
import com.uitgis.ciams.mapper.CiamsSsoUserMapper;
import com.uitgis.ciams.model.CiamsSsoUser;
import com.uitgis.ciams.model.OAuth2UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final CiamsSsoUserMapper ssoUserMapper;

	@Value("${auth.user-fail-cnt}")
	private Integer loginFailCnt;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		CiamsSsoUserDto.Data userDto = ssoUserMapper.selectById(loginId);

		if (userDto == null) {
			throw new UsernameNotFoundException("LoginIdNotFound => " + loginId);
		}

		OAuth2UserDetails userDetails = new OAuth2UserDetails();
		BeanUtils.copyProperties(userDto, userDetails);

		// 계정 상태 확인
		checkAccountStatus(userDto, userDetails);

		// 권한 설정
		userDetails.setAuthorities(getAuthorities(userDto));

		log.info("Login successful for user: {}", loginId);

		return userDetails;
	}

	private void checkAccountStatus(CiamsSsoUserDto.Data userDto, OAuth2UserDetails userDetails) {
		if (!userDetails.isEnabled()) {
			log.warn("Account not approved for user: {}", userDto.getLoginId());
			throw new LockedException("Waiting for account approval");
		}

		if (loginFailCnt != 0 && userDto.getLoginFailCnt() >= loginFailCnt) {
			lockUser(userDto);
			log.error("Account locked for user: {}", userDto.getLoginId());
			throw new LockedException("User account is locked");
		}

	}

	private List<GrantedAuthority> getAuthorities(CiamsSsoUserDto.Data userDto) {
		return RoleEnum.ROLE_ADMIN.getType().equals(userDto.getUserRole()) ?
				List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) :
				List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	private void lockUser(CiamsSsoUserDto.Data user) {
		CiamsSsoUser modify = new CiamsSsoUser();
		modify.setLoginId(user.getLoginId());
		modify.setLock(true);

		ssoUserMapper.updateById(modify);
	}

}
