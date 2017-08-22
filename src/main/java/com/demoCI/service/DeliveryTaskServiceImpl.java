/**
 * 
 */
package com.demoCI.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demoCI.exception.DeliveryTaskNotFoundException;
import com.demoCI.model.DeliveryPoint;
import com.demoCI.model.DeliveryTask;
import com.demoCI.repository.DeliveryTaskRepository;

/**
 * @author Med
 * 12 août 2017
 */
@Service
@Transactional
public class DeliveryTaskServiceImpl implements DeliveryTaskService {

	private static final String MSG_NOT_FOUND = "Delivery Task not found";
	private static final String MSG_PAGE_NOT_FOUND = "You don't have any Delivery Task";
	private static final String MSG_PAGE_COMPLETED_NOT_FOUND = "You don't have any completed Delivery Task";
	private static final String MSG_PAGE_RESERVED_NOT_FOUND = "You don't have any reserved Delivery Task";
	
	
	private DeliveryTaskRepository dTaskRepo;
	private UserService userService;
	
	@Autowired
	public DeliveryTaskServiceImpl(DeliveryTaskRepository dTaskRepo, UserService userService){
		this.dTaskRepo = dTaskRepo;
		this.userService = userService;
	} 
	
	@Override
	public DeliveryTask create(DeliveryTask dTask) {
		// TODO Auto-generated method stub
		
		return dTaskRepo.save(dTask);
	}

	@Override
	public DeliveryTask getReference(Long id) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = dTaskRepo.getOne(id);
		if(dTask == null) throw new DeliveryTaskNotFoundException(MSG_NOT_FOUND);
		return dTask;
	}
	
	@Override
	public DeliveryTask read(Long id, Long idCreator) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = dTaskRepo.findByIdAndCreatorId(id, idCreator);
		if(dTask == null) throw new DeliveryTaskNotFoundException(MSG_NOT_FOUND);
		return dTask;
	}

	@Override
	public DeliveryTask update(DeliveryTask dTask) {
		// TODO Auto-generated method stub
		return dTaskRepo.save(dTask);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dTaskRepo.delete(id);
	}

	@Override
	public Page<DeliveryTask> findByPage(Long idUser, int page, int size) {
		// TODO Auto-generated method stub
		Page<DeliveryTask> pageDTask = dTaskRepo.findByCreatorId(idUser, new PageRequest(page, size));
		
		if(pageDTask == null) throw new DeliveryTaskNotFoundException(MSG_PAGE_NOT_FOUND);
		
		return pageDTask;
	}

	@Override
	public Page<DeliveryTask> findCompleted(Long idUser, boolean completed, int page, int size) {
		// TODO Auto-generated method stub
		Page<DeliveryTask> pageDTask = dTaskRepo.findByCreatorIdAndCompleted(idUser, completed, new PageRequest(page, size));
		
		if(pageDTask == null) throw new DeliveryTaskNotFoundException(MSG_PAGE_COMPLETED_NOT_FOUND);
		
		return pageDTask;
	}

	@Override
	public Page<DeliveryTask> findReserved(Long idUser, boolean reserved, int page, int size) {
		// TODO Auto-generated method stub
		Page<DeliveryTask> pageDTask = dTaskRepo.findByCreatorIdAndReserved(idUser, reserved, new PageRequest(page, size));
		
		if(pageDTask == null) throw new DeliveryTaskNotFoundException(MSG_PAGE_RESERVED_NOT_FOUND);
		return pageDTask;
	}

	@Override
	public void switchFinishedStatePoint(Long idPoint,Long idtask, Long idCreator, String state) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = read(idtask, idCreator);
		dTask.getDeliveryPoints().forEach(p -> {
			if(p.getId() == idPoint){
				
				//calculate progress
				float realProgress = calculateProgress(state, dTask, p);
				float progress = realProgress > 99.00f ? 100.00f : realProgress;
				p.setFinished(state);
				dTask.setProgress(progress);
				//if all points delivered we set task complete to true
				if(progress == 100.00f){
					dTask.setCompleted(true);
				}else{
					dTask.setCompleted(false);
				}
			}
		});
		
		update(dTask);
		
	}
	
	@Override
	public void setReserved(Long idTask, Long idDeliveryMan, Boolean reserved) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = getReference(idTask);
		dTask.setDeliveryMan(userService.getReference(idDeliveryMan).getUsername());
		dTask.setReserved(reserved);
	}
	
	private float calculateProgress(String state, DeliveryTask dTask, DeliveryPoint p){
		float pointSize = dTask.getDeliveryPoints().size();
		float progress = dTask.getProgress();
		String finished = p.getFinished();
		//recalculate the progress of the Task.
		if(state.equals("Yes") && !finished.equals("Yes")){
			
			progress += 1/pointSize*100;
		}else if(!state.equals("Yes") && finished.equals("Yes")){
			
			progress -= 1/pointSize*100;
		}
		return progress;
	}


}
