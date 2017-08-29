/**
 * 
 */
package com.demoCI.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demoCI.controller.dto.DPointStateDTO;
import com.demoCI.controller.dto.DeliveryTaskDTO;
import com.demoCI.controller.dto.ReservedTaskDTO;
import com.demoCI.exception.DeliveryTaskNotFoundException;
import com.demoCI.model.DeliveryTask;
import com.demoCI.service.DeliveryTaskService;
import com.demoCI.service.PageOf;
import com.google.common.reflect.TypeToken;

/**
 * @author Med
 * 25 août 2017
 */
@RestController
@RequestMapping("/api")
public class DeliveryTaskController {
	
	
	private DeliveryTaskService dTaskService;
	private ModelMapper modelMapper;
	// will be replaced with a JWT
	private static final Map<String, String> JWT;
	private static final Type TYPE_LIST;
	static{
		JWT = new HashMap<String, String>();
		JWT.put("userId", "1");
		JWT.put("username", "username1");
		
		TYPE_LIST = new TypeToken<List<DeliveryTaskDTO>>() {}.getType(); 
	}
	
	@Autowired
	public DeliveryTaskController(DeliveryTaskService dTaskService, ModelMapper modelMapper) {
		this.dTaskService = dTaskService;
		this.modelMapper = modelMapper;
	}
	
	@RequestMapping(path = "/deliveryTasks/", method=RequestMethod.POST)
	public ResponseEntity<DeliveryTaskDTO> createTask(@RequestBody DeliveryTaskDTO dTaskDto) {
		dTaskDto.setCreatorId(Long.valueOf(JWT.get("userId")));
		
		if(dTaskDto.getReserved()){
			dTaskDto.setDeliveryMan(JWT.get("username"));
		}
		DeliveryTask dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
		return ResponseEntity.ok(modelMapper.map(dTaskService.create(dTask), DeliveryTaskDTO.class));
	}

	@RequestMapping(path = "/deliveryTasks/{taskId}", method=RequestMethod.GET)
	public ResponseEntity<DeliveryTaskDTO> readTask(@PathVariable Long taskId) {
		return ResponseEntity.ok(modelMapper.map(dTaskService.read(taskId), DeliveryTaskDTO.class));
	}

	@RequestMapping(path = "/deliveryTasks/", method=RequestMethod.PUT)
	public ResponseEntity<DeliveryTaskDTO> updateTask(@RequestBody DeliveryTaskDTO dTaskDto) {
		// here set Task creator from JWT
		try{
			// look if the task belong to the user
			dTaskService.read(dTaskDto.getId(), Long.valueOf(JWT.get("userId")));
			DeliveryTask dTask = modelMapper.map(dTaskDto, DeliveryTask.class);
			return ResponseEntity.ok(modelMapper.map(dTaskService.update(dTask), DeliveryTaskDTO.class));
		}catch(DeliveryTaskNotFoundException ex){
			throw ex;
		}
	}
	
	@RequestMapping(path = "/deliveryTasks/{taskId}", method=RequestMethod.DELETE)
	public void deleteTask(@PathVariable Long taskId) {
		try{
			// look if the task belong to the user
			dTaskService.read(taskId, Long.valueOf(JWT.get("userId")));
			dTaskService.delete(taskId);
		}catch(DeliveryTaskNotFoundException ex){
			throw ex;
		}
		
	}

	@RequestMapping(path = "/deliveryTasks/DeliveryPoints/finished/", method=RequestMethod.PATCH)
	public void switchFinishedStatePoint(@RequestBody DPointStateDTO dPointStateDto) {
		String deliveryMan = JWT.get("username");
		dTaskService.switchFinishedStatePoint(dPointStateDto.getIdPoint(), 
				dPointStateDto.getIdTask(), 
				deliveryMan, 
				dPointStateDto.getState());
	}
	
	@RequestMapping(path = "/deliveryTasks/reservedTrue/", method=RequestMethod.PATCH)
	public void setReservedTrue(@RequestBody ReservedTaskDTO rTaskdto) {
		String deliveryMan = JWT.get("username");
		dTaskService.setReservedTrue(rTaskdto.getDeliveryTaskId(), deliveryMan);
	}

	@RequestMapping(path = "/deliveryTasks/reservedFalse/", method=RequestMethod.PATCH)
	public void setReservedFalse(@RequestBody ReservedTaskDTO rTaskdto) {
		Long idCreator = Long.valueOf(JWT.get("userId"));
		dTaskService.setReservedFalse(rTaskdto.getDeliveryTaskId(), idCreator);
	}
	
	@RequestMapping(path = "/deliveryTasks/{page}/{size}/", method=RequestMethod.GET)
	public ResponseEntity<PageOf<DeliveryTaskDTO>> findAll(@PathVariable int page, @PathVariable int size) {
		
		PageOf<DeliveryTask> dTasksPage = dTaskService.findAll(page, size);
		PageOf<DeliveryTaskDTO> dTaskDtoPage = createPageOfDTO(dTasksPage);
		return ResponseEntity.ok(dTaskDtoPage);
	}

	@RequestMapping(path = "/deliveryTasks/creator/{page}/{size}/", method=RequestMethod.GET)
	public ResponseEntity<PageOf<DeliveryTaskDTO>> findPageByUser(@PathVariable int page, @PathVariable int size) {
		
		Long creator = Long.valueOf(JWT.get("userId"));
		PageOf<DeliveryTask> dTasksPage = dTaskService.findPageByUser(creator, page, size);
		PageOf<DeliveryTaskDTO> dTaskDtoPage = createPageOfDTO(dTasksPage);
		return ResponseEntity.ok(dTaskDtoPage);
	}
	
	@RequestMapping(path = "/deliveryTasks/creator/completed/{completed}/{page}/{size}/", method=RequestMethod.GET)
	public ResponseEntity<PageOf<DeliveryTaskDTO>> findCompleted(@PathVariable boolean completed, @PathVariable int page, @PathVariable int size) {
		
		Long creator = Long.valueOf(JWT.get("userId"));
		PageOf<DeliveryTask> dTasksPage = dTaskService.findCompleted(creator, completed, page, size);
		PageOf<DeliveryTaskDTO> dTaskDtoPage = createPageOfDTO(dTasksPage);
		return ResponseEntity.ok(dTaskDtoPage);
	}

	@RequestMapping(path = "/deliveryTasks/creator/reserved/{reserved}/{page}/{size}/", method=RequestMethod.GET)
	public ResponseEntity<PageOf<DeliveryTaskDTO>> findReserved(@PathVariable boolean reserved, @PathVariable int page, @PathVariable int size) {
		
		Long creator = Long.valueOf(JWT.get("userId"));
		PageOf<DeliveryTask> dTasksPage = dTaskService.findReserved(creator, reserved, page, size);
		PageOf<DeliveryTaskDTO> dTaskDtoPage = createPageOfDTO(dTasksPage);
		return ResponseEntity.ok(dTaskDtoPage);
	}
	
	@RequestMapping(path = "/deliveryTasks/deliveryMan/{page}/{size}/", method=RequestMethod.GET)
	public ResponseEntity<PageOf<DeliveryTaskDTO>> findByDeliveryMan(@PathVariable int page, @PathVariable int size) {
		
		String deliveryMan = JWT.get("username");
		PageOf<DeliveryTask> dTasksPage = dTaskService.findByDeliveryMan(deliveryMan, page, size);
		PageOf<DeliveryTaskDTO> dTaskDtoPage = createPageOfDTO(dTasksPage);
		return ResponseEntity.ok(dTaskDtoPage);
	}
	
	@RequestMapping(path = "/deliveryTasks/deliveryMan/{completed}/{page}/{size}/", method=RequestMethod.GET)
	public ResponseEntity<PageOf<DeliveryTaskDTO>> findDeliveryManAndCompleted(@PathVariable boolean completed, @PathVariable int page, @PathVariable int size) {
		
		String deliveryMan = JWT.get("username");
		PageOf<DeliveryTask> dTasksPage = dTaskService.findDeliveryManAndCompleted(deliveryMan, completed, page, size);
		PageOf<DeliveryTaskDTO> dTaskDtoPage = createPageOfDTO(dTasksPage);
		return ResponseEntity.ok(dTaskDtoPage);
	}
	
	private PageOf<DeliveryTaskDTO> createPageOfDTO(PageOf<DeliveryTask> dTasksPage){
		List<DeliveryTaskDTO> dTaskDtoList = modelMapper.map(dTasksPage.getContents(), TYPE_LIST);
		return new PageOf<>(dTaskDtoList, 
				dTasksPage.getPage(), 
				dTasksPage.getSize(),
				dTasksPage.getTotalValues(),
				dTasksPage.getTotalPages());
	}
}
