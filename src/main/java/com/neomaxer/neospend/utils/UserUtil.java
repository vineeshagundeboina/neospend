package com.neomaxer.neospend.utils;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.repositories.auth.UserRepo;

@Component
public class UserUtil {
	@Autowired
	UserRepo userrepo;
	private static final Logger log4j = LogManager.getLogger(UserUtil.class);

	public User getCurrentUser() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String user_name = ((UserDetails) principal).getUsername();
				log4j.info("Username:" + user_name);
				Optional<User> user = userrepo.findByUserName(user_name);
				if (user.isPresent()) {
					return user.get();
				}
			} else {
				String user_name = principal.toString();
				log4j.info("Username obj:" + user_name);
				Optional<User> user = userrepo.findByUserName(user_name);
				if (user.isPresent()) {
					return user.get();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
