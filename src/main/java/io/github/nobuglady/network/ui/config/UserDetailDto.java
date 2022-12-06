package io.github.nobuglady.network.ui.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailDto implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities = new ArrayList<>();
	
	public UserDetailDto(int userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getUserId() {
		return this.userId;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
