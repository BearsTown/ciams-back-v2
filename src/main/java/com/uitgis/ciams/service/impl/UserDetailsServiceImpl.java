package com.uitgis.ciams.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uitgis.ciams.dto.CiamsSsoUserDto;
import com.uitgis.ciams.enums.RoleEnum;
import com.uitgis.ciams.mapper.CiamsSsoUserMapper;
import com.uitgis.ciams.model.OAuth2UserDetails;
import com.uitgis.ciams.model.CiamsSsoUser;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final CiamsSsoUserMapper ciamsSsoUserMapper;

	@Value("${auth.user-fail-cnt}")
	private Integer loginFailCnt;

	@Override
	public UserDetails loadUserByUsername(String login_id) throws UsernameNotFoundException {
		CiamsSsoUserDto.Data userDto = ciamsSsoUserMapper.selectById(login_id);

		if (userDto == null) {
			throw new UsernameNotFoundException("LoginIdNotFound => " + login_id);
		}

		OAuth2UserDetails userDetails = new OAuth2UserDetails();
		BeanUtils.copyProperties(userDto, userDetails);

		if(loginFailCnt != 0){
			if(userDto.getLoginFailCnt() + 1 >= loginFailCnt){
				lockUser(userDto);
				userDto.setLock(true);
			}else{
				// 실패횟수 자동 증가 시킨후 로그인 성공시 0으로 변경
				ciamsSsoUserMapper.updateLoginFailCnt(login_id);
			}
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		//권한체크
		if(RoleEnum.ROLE_ADMIN.getType().equals(userDto.getUserRole())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if(RoleEnum.ROLE_MANAGER.getType().equals(userDto.getUserRole())){
			authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		}else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		userDetails.setAuthorities(authorities);

		return userDetails;
	}

	private void lockUser(CiamsSsoUserDto.Data user) {
		CiamsSsoUser modify = new CiamsSsoUser();
		modify.setLoginId(user.getLoginId());
		modify.setLock(true);

		ciamsSsoUserMapper.updateById(modify);
	}

}
