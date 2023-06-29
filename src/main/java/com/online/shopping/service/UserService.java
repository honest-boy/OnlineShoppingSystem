package com.online.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.shopping.model.CustomUserDetail;
import com.online.shopping.model.Role;
import com.online.shopping.model.User;
import com.online.shopping.repository.RoleRepository;
import com.online.shopping.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public void registerUser(User user) {
		String email = user.getEmail();
		try {
			if(userRepository.findUserByEmail(email).isPresent()) {
				logger.info("you are already present, please login");
			}else {
				String password = user.getPassword();
				user.setPassword(bCryptPasswordEncoder.encode(password));
				List<Role> roles = new ArrayList<>();
				roles.add(roleRepository.findById(2).get());
				user.setRoles(roles);
				userRepository.save(user);
			}
		}catch (Exception e) {
			logger.warn("invalid, please provide valid details");
		}
	}

	public void updateUser(User user) {
		try {
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findById(2).get());
			user.setRoles(roles);
			userRepository.save(user);
		} catch (Exception e) {
			logger.warn("user details not found");
		}
	}

	public User getCurrentLoggedInUser(Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();

		if(authentication == null) return null;
		User user = null;

		CustomUserDetail principal = (CustomUserDetail) authentication.getPrincipal();
		if(principal instanceof CustomUserDetail) {
			user = principal;
		}
		return user;
	}
	
	public Optional<User> findUserByEmail(String email){
		return userRepository.findUserByEmail(email);
	}

}
