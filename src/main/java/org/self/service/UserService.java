package org.self.service;

import static java.util.Collections.emptyList;
import org.self.model.User;
import org.self.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService, UserDetailsService {

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User createNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Override
	public User findByUserName(String username) {
		User user = repository.findByUsername(username);
		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = repository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
	}

	@Override
	public boolean isUsernameExist(String username) {
		User user = repository.findByUsername(username);
		return user != null;
	}

}