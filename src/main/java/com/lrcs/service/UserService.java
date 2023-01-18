package com.lrcs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lrcs.entities.User;
import com.lrcs.entities.DTO.UserDTO;
import com.lrcs.repository.UserRepository;
import com.lrcs.service.exceptions.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository ur, BCryptPasswordEncoder pwe) {
		this.userRepository = ur;
		this.passwordEncoder = pwe;
	}
	
	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	public UserDTO findById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("User not found"));
		 return new UserDTO(user);
	}
	
	public UserDTO insert(UserDTO userDTO) {
		User user = new User();
		copyToEntity(userDTO, user);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return new UserDTO(userRepository.save(user));
	}
	
	public void copyToEntity(UserDTO dto, User user) {
		user.setEmail(dto.getEmail());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		return user;
	}
}
