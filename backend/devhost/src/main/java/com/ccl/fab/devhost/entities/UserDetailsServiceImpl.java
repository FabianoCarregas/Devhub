package com.ccl.fab.devhost.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccl.fab.devhost.repositories.UserRepository;
import com.ccl.fab.devhost.security.UserSS;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userRepository.findByName(name);
		if ( name == null ){
			throw new UsernameNotFoundException(name);
		}
		
		return new UserSS(user.getId(), user.getPassword(), user.getName(), user.getProfiles());
		
	}

}
