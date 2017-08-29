/**
 * 
 */
package com.demoCI.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import com.demoCI.DemoCiApplication;
import com.demoCI.controller.dto.UserRegisterDTO;
import com.demoCI.model.User;
import com.demoCI.service.PageOf;
import com.demoCI.service.UserService;

/**
 * @author Med
 * 29 août 2017
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=DemoCiApplication.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerUnitTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	UserRegisterDTO userDto;
	User user;
	String userJSON = "{\"username\":\"usernameController\", \"email\":\"emailController\", \"password\":\"pass\"}";
	
	@Before
	public void initialize(){
		userDto = new UserRegisterDTO();
		userDto.setUsername("usernameController");
		userDto.setEmail("emailController");
		userDto.setPassword("pass");
		user = new User("usernameController", "emailController", "pass");
		user.setId(33L);
	}
	
	@Test
	public void create_user_from_userDto_test() throws Exception{
		
		when(userService.create(Mockito.any(User.class))).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/")
				.accept(MediaType.APPLICATION_JSON).content(userJSON).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void find_user_by_id_test() throws Exception{
		
		when(userService.read(Mockito.anyLong())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/33")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void find_user_by_username_test() throws Exception{
		
		when(userService.findByUsername(Mockito.anyString())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users?username=usernameController")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void find_user_page_test() throws Exception{
		
		when(userService.findByPage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new PageOf<User>());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users?page=0")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void update_user_test() throws Exception{
		
		when(userService.update((Mockito.any(User.class)))).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/users/")
				.accept(MediaType.APPLICATION_JSON).content(userJSON).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void delete_user_test() throws Exception{
		
		doNothing().when(userService).delete((Mockito.anyLong()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/users/33")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("User deleted", response.getContentAsString());
	}
}
