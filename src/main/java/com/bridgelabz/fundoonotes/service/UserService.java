package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.entity.User;


public interface UserService {

	public void register(UserDTO userDto);
	
	public List<User> getAllUsers();
	
	//public String login()
}
