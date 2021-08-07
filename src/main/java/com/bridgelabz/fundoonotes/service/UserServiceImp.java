package com.bridgelabz.fundoonotes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.FundooException;
import com.bridgelabz.fundoonotes.repository.UserRepository;

@Service
public class UserServiceImp  implements UserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void register(UserDTO userDto) {
		Optional<User> isUserPresent = userRepository.findByEmailId(userDto.getEmailId());
		
		if(isUserPresent.isPresent()) {
			throw new FundooException(HttpStatus.CONFLICT.value(),"Email is already present");
		}
		
		//can use model mapper or constructor to inject data
		User user = new User();
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		BeanUtils.copyProperties(userDto, user);
		user.setIsVerified(false);
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
