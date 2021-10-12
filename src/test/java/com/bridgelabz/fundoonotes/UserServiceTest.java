package com.bridgelabz.fundoonotes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.UserServiceImp;
import com.bridgelabz.fundoonotes.utils.TokenService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private List<User> users;
	
	private LoginDTO loginDto;
	
	private User user;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@Mock
	TokenService tokenService;
	
	@InjectMocks
	UserServiceImp userService;
	
	@Before
	public void setup() {
		this.users = new ArrayList<User>();
		users.add(new User((long) 1, "venkat", "Reddy", "venkat@gmail.com", "9966670662", true, "", "AdminAdmin", "", null, null, null));
		users.add(new User((long) 2, "aniket", "chile", "aniket@gmail.com", "9966670662", true, "", "AdminAdmin", "", null, null, null));
		this.loginDto = new LoginDTO("venkat@gmail.com", "admin@123"); 
		this.user = users.get(0);
	}
	
	@Test
	public void givenRequiredParameters_WhenPoper_ShouldRetrunUsers() {
		when(userRepository.findAll()).thenReturn(users);
		List<User> result = userService.getAllUsers();
		Assert.assertEquals(users, result);
	} 
	
	@Test
	public void givenLoginDetails_WhenProper_ShouldReturnToken() {
		//verify(userRepository.deleteAll());
		when(userRepository.findByEmailId(any())).thenReturn(Optional.of(user));
		when(passwordEncoder.matches(any(), any())).thenReturn(true);
		when(tokenService.createToken(any())).thenReturn("vdvdvxxzac");
		String token = userService.login(loginDto);
		Assert.assertEquals("vdvdvxxzac", token);
	}
}
