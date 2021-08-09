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
import com.bridgelabz.fundoonotes.utils.EmailService;
import com.bridgelabz.fundoonotes.utils.TokenService;

@Service
public class UserServiceImp  implements UserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private EmailService emailService;
	
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
		User savedUser = userRepository.save(user);
		boolean isEmailSent = emailService.sendMail("Verify Your Email", "http://localhost:8081/user/verify/"+tokenService.createToken(savedUser.getId()), savedUser.getEmailId(), "venakattestmail@gmail.com");
		if(!isEmailSent)
			throw new FundooException(HttpStatus.BAD_REQUEST.value(), "User is registered but error while sending email verification link");
	}

	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	@Override
	public void verifyUser(String token) {
		Long userId = tokenService.decodeToken(token);
		User user = userRepository.findById(userId).orElseThrow(
				                          () -> new FundooException(HttpStatus.NOT_FOUND.value(), "User Not Found"));
		user.setIsVerified(true);
		userRepository.save(user);
	}

}
