package com.bridgelabz.fundoonotes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.fundoonotes.response.Response;
import com.google.gson.Gson;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FundoonotesApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
//	@Autowired
//	private UserController userController;
//	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void givenRequiredParameters_WhenProper_ShouldReturnUsers() throws Exception {
	MvcResult result = mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
	 Response response = new Gson()
             .fromJson(result.getResponse().getContentAsString(), Response.class);	
		Assert.assertEquals(200,response.getStatusCode());
	}

}
