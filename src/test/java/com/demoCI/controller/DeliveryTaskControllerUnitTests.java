/**
 * 
 */
package com.demoCI.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demoCI.controller.dto.DeliveryPointDTO;
import com.demoCI.controller.dto.DeliveryTaskDTO;
import com.demoCI.model.DeliveryPoint;
import com.demoCI.model.DeliveryTask;
import com.demoCI.model.User;
import com.demoCI.service.DeliveryTaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Med
 * 30 août 2017
 */
@RunWith(SpringRunner.class)
//If we don't want use use a mock for ModelMapper we use this @ContextConfiguration(classes=DemoCiApplication.class)
@WebMvcTest(value = DeliveryTaskController.class, secure = false)
public class DeliveryTaskControllerUnitTests {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private DeliveryTaskService dTaskService;
	@MockBean
	private ModelMapper modelMapper;
	User user;
	DeliveryTask dTask;
	DeliveryPoint dPoint1;
	DeliveryPoint dPoint2;
	DeliveryTaskDTO dTaskDto;
	String dTaskJSON;
	
	@Before
	public void initialize(){
		user = new User("usernameController", "emailController", "pass");
		user.setId(33L);
		dTask = new DeliveryTask("task1", false, false, user);
		dTask.setDeliveryMan(null);
		dTask.setId(1L);
		dPoint1 = new DeliveryPoint("description1", "No", 10d, 20d);
		dPoint1.setId(1L);
		dPoint2 = new DeliveryPoint("description2", "No", 15d, 41d);
		dPoint2.setId(2L);
		dTask.setDeliveryPoints(Arrays.asList(dPoint1, dPoint2));

		DeliveryPointDTO dPointDto1 = new DeliveryPointDTO("description1", "No", 10d, 20d, null);
		DeliveryPointDTO dPointDto2 = new DeliveryPointDTO("description2", "No", 15d, 41d, null);
		dTaskDto = new DeliveryTaskDTO("task1", null, null, false, 0.0f, false, null);
		dTaskDto.setDeliveryPoints(Arrays.asList(dPointDto1, dPointDto2));
		ObjectMapper mapper = new ObjectMapper();
		try {
			dTaskJSON = mapper.writeValueAsString(dTaskDto);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void create_delivery_task_unit_test() throws Exception {
		when(dTaskService.create(Mockito.any(DeliveryTask.class))).thenReturn(dTask);
		
		RequestBuilder requestbuilder = MockMvcRequestBuilders.post("/api/deliveryTasks/")
				.accept(MediaType.APPLICATION_JSON).content(dTaskJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestbuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertNotNull(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void read_delivery_task_by_id_unit_test() throws Exception {
		when(dTaskService.read(Mockito.anyLong())).thenReturn(dTask);
		
		RequestBuilder requestbuilder = MockMvcRequestBuilders.get("/api/deliveryTasks/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestbuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertNotNull(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void update_delivery_task_unit_test() throws Exception {
		when(dTaskService.read(Mockito.anyLong(), Mockito.anyLong())).thenReturn(dTask);
		when(dTaskService.update(Mockito.any(DeliveryTask.class))).thenReturn(dTask);
		RequestBuilder requestbuilder = MockMvcRequestBuilders.put("/api/deliveryTasks/")
				.accept(MediaType.APPLICATION_JSON).content(dTaskJSON).contentType(MediaType.APPLICATION_JSON);;
		MvcResult result = mockMvc.perform(requestbuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertNotNull(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void delete_delivery_task_unit_test() throws Exception {
		when(dTaskService.read(Mockito.anyLong(), Mockito.anyLong())).thenReturn(dTask);
		doNothing().when(dTaskService).delete(Mockito.anyLong());
		RequestBuilder requestbuilder = MockMvcRequestBuilders.delete("/api/deliveryTasks/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestbuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
