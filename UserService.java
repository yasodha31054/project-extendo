package com.example.prj;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUserName(username);
		if (!userOptional.isPresent()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		User user = userOptional.get();
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> getAllUSer() {
		return userRepository.findAll();
	}

	public User addUSer(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUser(int userid) {
		return userRepository.findById(userid);

	}

	public User updateUser(User userid) {
		Optional<User> existingUser = userRepository.findById(userid.getUserId());
		if (existingUser.isPresent()) {
			existingUser.get().setUserName(userid.getUserName());
			existingUser.get().setPassword(userid.getPassword());
			existingUser.get().setStudentId(userid.getStudentId());
			return userRepository.saveAndFlush(existingUser.get());
		}
		return null;
	}

	public boolean deleteUser(int userid) {
		Optional<User> user = userRepository.findById(userid);
		if (user.isPresent()) {
			userRepository.deleteById(userid);
			return true;
		}
		return false;
	}

	public User getUserDetails(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password);
	}

}
