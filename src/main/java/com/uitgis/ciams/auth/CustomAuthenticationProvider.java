package com.uitgis.ciams.auth;

import com.uitgis.ciams.admin.mapper.AdminUserMapper;
import com.uitgis.ciams.config.UitPasswordEncoder;
import com.uitgis.ciams.user.dto.CiamsLoginLogDto;
import com.uitgis.ciams.admin.mapper.AdminLoginLogMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    private final AdminUserMapper adminUserMapper;
    private final AdminLoginLogMapper adminLoginlogMapper;

	@Autowired
    public void setCustomUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService); // 반드시 설정 필요
    }

	@Autowired
    public void setCustomPasswordEncoder() {
        super.setPasswordEncoder(uitPasswordEncoder()); // PasswordEncoder 설정
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 사용자 정보 조회
        UserDetails user = getUserDetailsService().loadUserByUsername(username);


        if(null == user) {	// 사용자 조회
        	throw new UsernameNotFoundException("User not found: " + username);
        }else if(!getPasswordEncoder().matches(password, user.getPassword())) {	//패스워드 검증
        	// 로그인 실패 횟수 증가
            adminUserMapper.updateLoginFailCnt(username);
        	throw new BadCredentialsException("Invalid password");
        }else {
        	 log.debug("로그인 성공! 후처리 실행 - 사용자: " + username);
        	// 로그인 실패 횟수 초기화
            adminUserMapper.initLoginFailCnt(username);
        	addLoginHistory(username, getClientIp());
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    public PasswordEncoder uitPasswordEncoder() {
        return new UitPasswordEncoder();
    }

    private void addLoginHistory(String username, String ip) {
        CiamsLoginLogDto.Add add = CiamsLoginLogDto.Add.builder()
				.loginId(username)
				.ip(ip)
				.build();

        adminLoginlogMapper.insert(add);
	}

    private String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            // `X-Forwarded-For` 값이 여러 개일 경우, 첫 번째 값이 실제 클라이언트 IP
            return ip.split(",")[0].trim();
        }

        // 프록시 환경이 아닐 경우 다른 헤더 값 체크
        ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();  // 기본 IP 주소
        }

        return ip;
    }
}
