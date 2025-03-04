package com.uitgis.ciams.service;

import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsCommonService {

	/**
	 * 관리자 여부
	 * @return true / false
	 */
	public boolean isAdmin() {
		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext()
				.getAuthentication()
				.getAuthorities();
		return roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	/**
	 * 세션 사용자
	 * @return 로그인 아이디
	 */
	public String getUsername(){
//		return SecurityContextHolder.getContext().getAuthentication().getName();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			// 사용자 이름 확인
			String username = authentication.getName(); // 기본적으로 Principal의 이름 (username or client_id)
			System.out.println("Principal Name: " + username);

			// 권한 확인
			authentication.getAuthorities().forEach(authority -> {
				System.out.println("Authority: " + authority.getAuthority());
			});

			// 추가 정보 확인 (JWT 사용 시 Claims)
			if (authentication.getPrincipal() instanceof Jwt) {
				Jwt jwt = (Jwt) authentication.getPrincipal();
				String clientId = jwt.getClaimAsString("client_id"); // 클라이언트 ID
				String userName = jwt.getClaimAsString("user_name"); // 사용자 이름
				System.out.println("Client ID: " + clientId);
				System.out.println("User Name: " + userName);
			}
		}


		return authentication.getName();
	}

	/**
	 * 시스템 로그
	 * @param menu
	 * @param actionType
	 * @param keys
	 */
	public void log(MenuEnum menu, ActionTypeEnum actionType, List<String> keys) {
		String key = "";
		key = keys.stream().map(a->String.valueOf(a)).collect(Collectors.joining(","));
		log(menu,actionType, key);
	}

	/**
	 * 시스템 로그(메인)
	 * @param menu
	 * @param actionType
	 * @param key
	 */
	public void log(MenuEnum menu, ActionTypeEnum actionType, String key) {
		log.info("[ Ciams System Log ] : Menu:[{}] Action:[{}] Key:[{}] LoginId:[{}]", menu.getName() , actionType.getName(), key, getUsername());
	}

}
