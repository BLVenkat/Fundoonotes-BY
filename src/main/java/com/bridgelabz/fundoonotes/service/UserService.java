package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.entity.User;


public interface UserService {

	public void register(UserDTO userDto);
	
	public void verifyUser(String token);
	
	public List<User> getAllUsers();
	
	public String login(LoginDTO loginDto);
	
	public void forgotPassword(String emailId);
	
	public void restPassword(String password,String token);
	
	public String profilePic(String token,MultipartFile file);
}
