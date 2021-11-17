package com.ccl.fab.devhost.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ccl.fab.devhost.security.UserSS;

public class UsersService {
	
	public static UserSS autheticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
