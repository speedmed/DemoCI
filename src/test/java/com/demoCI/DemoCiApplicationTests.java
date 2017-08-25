package com.demoCI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.demoCI.controller.dto.DeliveryPointDTO;
import com.demoCI.controller.dto.DeliveryTaskDTO;
import com.demoCI.exception.DeliveryTaskNotFoundException;
import com.demoCI.exception.UserNotFoundException;
import com.demoCI.model.DeliveryPoint;
import com.demoCI.model.DeliveryTask;
import com.demoCI.model.Role;
import com.demoCI.model.User;
import com.demoCI.service.DeliveryTaskService;
import com.demoCI.service.UserService;
import com.demoCI.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DemoCiApplicationTests {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DeliveryTaskService dTaskService;
	@Autowired
	private UserService userService;
	
	@Test
	public void unit_test_user() {
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
		
		assertNull(mockUserService.findByUsername("user1"));
		assertEquals("email1", mockUserService.findByUsername("UpdatedUser1").getEmail());
	}
	
	@Test
	public void test_integration_add_user(){
		User user = new User("user1", "email1", "pass1");
		user.addRole(new Role("ADMIN"));
		userService.create(user);
		User u = userService.findByUsername("user1");
		assertEquals("email1", u.getEmail());
		assertEquals(1, u.getRoles().size());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void test_findByUsername_shouldThrowUserNotFoundException(){
		
		assertThat(userService.findByUsername("user2"));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void test_readById_shouldThrowUserNotFoundException(){
		
		assertThat(userService.read(3L));
	}
	@Test(expected = DeliveryTaskNotFoundException.class)
	public void test_integration_DeliveryTask(){
		// Create Delivery task with user and points
		User user = new User("user1", "email1", "pass1");
		user.addRole(new Role("ADMIN"));
		userService.create(user);
		User u = userService.findByUsername("user1");
		
		DeliveryPoint dPoint1 = new DeliveryPoint("description1 point", "No", 15d, 30d);
		DeliveryPoint dPoint2 = new DeliveryPoint("description2 point", "No", 20d, 11d);
		
		DeliveryTask dTask = new DeliveryTask("task1", false, false, u);
		dTask.addDeliveryPoint(dPoint1);
		dTask.addDeliveryPoint(dPoint2);
		dTask.setCreatedDate(new Date());
		dTask.setProgress(0.0f);
		DeliveryTask dTaskCreated = dTaskService.create(dTask);
		
		assertNotNull(dTaskCreated.getId());
		assertEquals("task1", dTaskCreated.getTaskName());
		assertEquals("description2 point", dTaskCreated.getDeliveryPoints().get(1).getDescription());
		assertEquals("user1", dTaskCreated.getCreator().getUsername());
		assertNotNull(dTaskCreated.getCreator().getId());
/* ********************************************************************************************************************************  */
		//Reserve a Delivery Task
		dTaskService.setReservedTrue(dTask.getId(), u.getUsername());
		assertTrue(dTask.getReserved());
		assertEquals("user1", dTask.getDeliveryMan());
/* ********************************************************************************************************************************* */		
		
		//Switch the state of the delivery point.
		dTaskService.switchFinishedStatePoint(1L, dTask.getId(), u.getUsername(), "Yes");
		dTaskService.switchFinishedStatePoint(2L, dTask.getId(), u.getUsername(), "Yes");
		assertTrue(dTask.getCompleted());
/* ********************************************************************************************************************************** */
		
		//Update Delivery task without fetching the entity in the DB
		DeliveryTask dTaskUpd = new DeliveryTask("taskUpdated", true, true, u);
		dTaskUpd.setId(1L);
		dTaskService.update(dTaskUpd);
		DeliveryTask updatedTask =  dTaskService.read(1L, u.getId());
		assertEquals("taskUpdated", updatedTask.getTaskName());
		assertEquals(0, updatedTask.getDeliveryPoints().size());
/* ********************************************************************************************************************************** */
		
		//Delete Delivery Task
		dTaskService.delete(updatedTask.getId());
		DeliveryTask deletedTask =  dTaskService.read(1L, u.getId());
		assertNull(deletedTask);
/* ********************************************************************************************************************************** */
	}
	
	@Test(expected = DeliveryTaskNotFoundException.class)
	public void test_shouldThrowDeliveryTaskNotFoundException(){
		
		assertThat(dTaskService.read(1L, 2L));
		assertThat(dTaskService.getReference(1L));
		assertThat(dTaskService.findPageByUser(2L, 1, 5));
		assertThat(dTaskService.findCompleted(2L, true, 1, 5));
		assertThat(dTaskService.findReserved(2L, true, 1, 5));
	}
	
	@Test
	public void test_modelMapper(){
		
		DeliveryPoint dPoint = new DeliveryPoint("description1", "No", 15d, 30d);
		dPoint.setId(10L);
		
		DeliveryTask dTask = new DeliveryTask("task1", false, false, new User(1L));
		dTask.setProgress(50.00f);
		dTask.addDeliveryPoint(dPoint);
		dTask.setId(5L);
		
		DeliveryTaskDTO taskDto = modelMapper.map(dTask, DeliveryTaskDTO.class);
		DeliveryPointDTO pointDto = modelMapper.map(dPoint, DeliveryPointDTO.class);
		
		System.out.println(taskDto);
		System.out.println("***********************************************************************");
		System.out.println(pointDto);
	}

}
