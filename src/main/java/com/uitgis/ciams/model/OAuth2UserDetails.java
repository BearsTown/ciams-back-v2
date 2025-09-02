package com.uitgis.ciams.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uitgis.ciams.user.dto.CiamsUserDto;

public class OAuth2UserDetails extends CiamsUserDto.Data implements UserDetails{

	private int access_level;

	private List<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return super.getUserPassword();
	}

	@Override
	public String getUsername() {
		return super.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		Boolean lock = super.getLock();
		return lock != null && !lock;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.getRoleYn().equals("Y");
	}

}
