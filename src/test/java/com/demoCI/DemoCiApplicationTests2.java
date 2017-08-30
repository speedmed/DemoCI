/**
 * 
 */
package com.demoCI;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demoCI.controller.dto.DeliveryPointDTO;
import com.demoCI.controller.dto.DeliveryTaskDTO;
import com.demoCI.controller.dto.ReservedTaskDTO;
import com.demoCI.controller.dto.UserRegisterDTO;
import com.demoCI.exception.DeliveryTaskNotFoundException;
import com.demoCI.model.DeliveryPoint;
import com.demoCI.model.DeliveryTask;
import com.demoCI.model.Role;
import com.demoCI.model.User;
import com.demoCI.service.DeliveryTaskService;
import com.demoCI.service.RoleService;
import com.demoCI.service.UserService;

/**
 * @author Med
 * 22 août 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DemoCiApplicationTests2 {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DeliveryTaskService dTaskService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	UserRegisterDTO userDto;
	DeliveryTaskDTO dTaskDto;
	DeliveryTask dTask;
	
	@Before
	public void initialize(){
		userDto = new UserRegisterDTO();
		userDto.setUsername("username1");
		userDto.setEmail("email1");
		userDto.setPassword("pass");
		
		DeliveryPointDTO dPointDto1 = new DeliveryPointDTO("point1", "No", 10d, 20d, null);
		DeliveryPointDTO dPointDto2 = new DeliveryPointDTO("point2", "No", 15d, 41d, null);
		dTaskDto = new DeliveryTaskDTO("task1", new Date(), new Date(System.currentTimeMillis()+600000), false, 0.0f, false, null);
		dTaskDto.setDeliveryPoints(Arrays.asList(dPointDto1, dPointDto2));

		
	}
	
	@Test
	public void test_should_add_user_from_userDTO() {
		Role role = roleService.create(new Role("USER"));
		User u = modelMapper.map(userDto, User.class);
		u.addRole(role);
		userService.create(u);
		
		assertNotNull(u.getId());
		assertEquals("username1", u.getUsername());
		assertEquals("email1", u.getEmail());
		assertEquals("pass", u.getPassword());
		assertEquals("ROLE_USER", u.getRoles().stream().map(r -> r.getRoleName()).findAny().orElse(null));
	}
	
	@Test
	public void test_should_addDeliveryTask_from_DTO(){
		User u = userService.create(modelMapper.map(userDto, User.class));
		dTaskDto.setCreatorId(u.getId());
		dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
		dTask = dTaskService.create(dTask);
		List<DeliveryPoint> dPoints = dTask.getDeliveryPoints(); 
		assertNotNull(dTask.getId());
		dPoints.forEach(dPoint -> {
			assertEquals(dTask.getId(), dPoint.getDeliveryTask().getId());
		});
		assertEquals("task1", dTask.getTaskName());
		assertEquals("point1", dPoints.get(0).getDescription());
	}
	
	@Test
	public void test_should_updateDeliveryTask_from_DTO_add_DeliveryPoint(){
		User u = userService.create(modelMapper.map(userDto, User.class));
		dTaskDto.setCreatorId(u.getId());
		dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
		dTask = dTaskService.create(dTask);
		//Updating from DTO
		DeliveryTaskDTO dTaskDtoUpd = modelMapper.map(dTask, DeliveryTaskDTO.class);
		//adding a new DeliveryPoint to existing delivery points
		DeliveryPointDTO newDPointDto = new DeliveryPointDTO("point3", "No", 17d, 5d, dTaskDtoUpd.getId());
		dTaskDtoUpd.getDeliveryPoints().add(newDPointDto);
		dTask = dTaskService.update(modelMapper.map(dTaskDtoUpd, DeliveryTask.class));
		
		assertEquals(3, dTask.getDeliveryPoints().size());
		
	}
	
	@Test
	public void test_should_updateDeliveryTask_from_DTO_delete_DeliveryPoint(){
		User u = userService.create(modelMapper.map(userDto, User.class));
		dTaskDto.setCreatorId(u.getId());
		dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
		dTask = dTaskService.create(dTask);
		//Updating from DTO
		DeliveryTaskDTO dTaskDtoUpd = modelMapper.map(dTask, DeliveryTaskDTO.class);
		
		//deleting a DeliveryPoint from the list of delivery points
		dTaskDtoUpd.getDeliveryPoints().remove(0);
		dTask = dTaskService.update(modelMapper.map(dTaskDtoUpd, DeliveryTask.class));
		assertEquals(1, dTask.getDeliveryPoints().size());
		assertEquals("point2", dTask.getDeliveryPoints().get(0).getDescription());
	}
	
	@Test
	public void test_should_reserve_DeliveryTask_from_DTO(){
		User u = userService.create(modelMapper.map(userDto, User.class));
		dTaskDto.setCreatorId(u.getId());
		dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
		dTask = dTaskService.create(dTask);
		//Updating from DTO
		DeliveryTaskDTO dTaskDtoUpd = modelMapper.map(dTask, DeliveryTaskDTO.class);
		ReservedTaskDTO reservedTaskDto = new ReservedTaskDTO(dTaskDtoUpd.getId());
		dTaskService.setReservedTrue(reservedTaskDto.getDeliveryTaskId(), u.getUsername());
		
		assertTrue(dTask.getReserved());
		assertEquals("username1", dTask.getDeliveryMan());
	}
	
	@Test
	public void test_should_cancel_reserve_DeliveryTask_from_DTO_by_creator(){
		User u = userService.create(modelMapper.map(userDto, User.class));
		User deliveryMan = userService.create(new User("userDelivery", "emailDelivery", "pass"));
		dTaskDto.setCreatorId(u.getId());
		dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
		dTask = dTaskService.create(dTask);
		//Updating from DTO
		DeliveryTaskDTO dTaskDtoUpd = modelMapper.map(dTask, DeliveryTaskDTO.class);
		ReservedTaskDTO reservedTaskDto = new ReservedTaskDTO(dTaskDtoUpd.getId());
		dTaskService.setReservedTrue(reservedTaskDto.getDeliveryTaskId(), deliveryMan.getUsername());
		assertEquals("userDelivery", dTask.getDeliveryMan());
		dTaskService.setReservedFalse(reservedTaskDto.getDeliveryTaskId(), u.getId());
		assertNull(dTask.getDeliveryMan());
		assertFalse(dTask.getReserved());
	}
	
	@Test(expected=DeliveryTaskNotFoundException.class)
	public void test_should_return_DeliveryTaskNotFoundException_(){
		User u = userService.create(modelMapper.map(userDto, User.class));
		User deliveryMan = userService.create(new User("userDelivery", "emailDelivery", "pass"));
		dTaskDto.setCreatorId(u.getId());
		dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
		dTask = dTaskService.create(dTask);
		//Updating from DTO
		DeliveryTaskDTO dTaskDtoUpd = modelMapper.map(dTask, DeliveryTaskDTO.class);
		ReservedTaskDTO reservedTaskDto = new ReservedTaskDTO(dTaskDtoUpd.getId());
		dTaskService.setReservedTrue(reservedTaskDto.getDeliveryTaskId(), deliveryMan.getUsername());
		// Here the exception 'cause deliveryMan is not the creator he can't cancel reservation only creator can cancel.
		dTaskService.setReservedFalse(reservedTaskDto.getDeliveryTaskId(), deliveryMan.getId());
	}
	@After
	public void end(){
		userDto = null;
		dTaskDto = null;
		dTaskService.flush();
		
	}
}
