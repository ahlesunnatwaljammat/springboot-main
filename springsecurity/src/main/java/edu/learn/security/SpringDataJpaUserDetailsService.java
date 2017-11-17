package edu.learn.security;

import edu.learn.jpa.entities.User;
import edu.learn.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	@Autowired
	public SpringDataJpaUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//User user = this.repository.findByUsername(username);
		final User user = new User();
		user.setUsername("nabbasi");
		//user.setPassword(User.PASSWORD_ENCODER.encode("x"));
		user.setPassword("x");
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_MANAGER"));
		return new UserDetails() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();
				auths.add(new SimpleGrantedAuthority("USER"));
				return auths;
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getUsername();
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
		};
	}
}