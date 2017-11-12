package edu.learn.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		edu.learn.jpa.entities.User user = new edu.learn.jpa.entities.User();
		user.setUsername(username);
		user.setPassword("x");
		return new User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList("ROLE_MANAGER"));
	}

}