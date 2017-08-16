package com.demoCI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.demoCI.model.User;
import com.demoCI.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoCiApplicationTests {

	@Test
	public void test_user() {
		UserServiceImpl mockUserService = mock(UserServiceImpl.class);
		
		User u1 = new User("user1", "email1", "pass1");
		User u2 = new User("user2", "email2", "pass2");
		User u3 = new User("user3", "email3", "pass3");
		List<User> listUsers = Arrays.asList(u1,u2,u3);
		Page<User> pageUsers = new PageImpl<>(listUsers);
		
		when(mockUserService.findByPage(0, 5)).thenReturn(pageUsers);
		when(mockUserService.create(u1)).thenReturn(u1);
		when(mockUserService.read(2L)).thenReturn(u2);
		
		int size = mockUserService.findByPage(0, 5).getNumberOfElements();
		User u = mockUserService.create(u1);
		User us2 = mockUserService.read(2L);
		assertEquals(size, 3);
		assertEquals("email1", u.getEmail());
		assertEquals("pass2", us2.getPassword());
		
		u1.setUsername("UpdatedUser1");
		
		when(mockUserService.update(u1)).thenReturn(u1);
		
		User upUser = mockUserService.update(u1);
		
		assertEquals("UpdatedUser1", upUser.getUsername());
		
		when(mockUserService.findByUsername("user1")).thenReturn(null);
		when(mockUserService.findByUsername("UpdatedUser1")).thenReturn(u1);
		
		assertEquals(null, mockUserService.findByUsername("user1"));
		assertEquals("email1", mockUserService.findByUsername("UpdatedUser1").getEmail());
	}

}
