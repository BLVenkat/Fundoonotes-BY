package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(description = "Controller for all the operations related to user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	@ApiOperation(value = "Api to register a user for fundoonotes application",response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201,message = "User registered Successfully"),
			@ApiResponse(code = 401,message = "User not found"),
			@ApiResponse(code = 422,message = "Request body is not valid")
	})
	public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userDto,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		userService.register(userDto);
		return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), "User Registered Successfully", ""), HttpStatus.CREATED);
	}
	
	@GetMapping()
	@ApiOperation("Api to get all the users of the application")
	public ResponseEntity<Response> getAllUser() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), "Users Fetched Successfully", users), HttpStatus.OK);
	}
	
	@PutMapping("/verify/{token}")
	@ApiOperation("Api to verify the user email adddress")
	public ResponseEntity<Response> verifyEmail(@PathVariable 
					@ApiParam(required = true,value = "user identity token") String token) {
	   userService.verifyUser(token);
		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), "Email Verified Successfully", ""), HttpStatus.OK);
	}

}
