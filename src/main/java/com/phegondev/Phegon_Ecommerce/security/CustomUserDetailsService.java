package com.phegondev.Phegon_Ecommerce.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phegondev.Phegon_Ecommerce.entity.User;
import com.phegondev.Phegon_Ecommerce.exception.NotFoundException;
import com.phegondev.Phegon_Ecommerce.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByEmail(username).orElseThrow(() -> new NotFoundException("User/ Email Not found"));
		return AuthUser.builder().user(user).build();
	}

}
