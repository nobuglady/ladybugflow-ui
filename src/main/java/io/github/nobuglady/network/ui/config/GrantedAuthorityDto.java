package io.github.nobuglady.network.ui.config;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityDto implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String authority;
	
	public GrantedAuthorityDto(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}

}
