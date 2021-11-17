package com.ccl.fab.devhost.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ccl.fab.devhost.enums.Profilee;



public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String password;
	private String name;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
	}
	
	public UserSS(Long id, String password, String name, Set<Profilee> profiles) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean hasRole(Profilee profile) {
		return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
		
	}

}
