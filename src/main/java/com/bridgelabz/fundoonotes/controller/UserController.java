package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userDto,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		userService.register(userDto);
		return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), "User Registered Successfully", ""), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<Response> getAllUser() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), "Users Fetched Successfully", users), HttpStatus.OK);
	}

}
