/**
 * 
 */
package com.demoCI.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	private static final String MSG_PAGE_NOT_FOUND = "You did not create any Delivery Task";
	private static final String MSG_PAGE_COMPLETED_TRUE_NOT_FOUND = "None of your delivery tasks are complete";
	private static final String MSG_PAGE_COMPLETED_FALSE_NOT_FOUND = "All your delivery tasks are complete";
	private static final String MSG_PAGE_RESERVED_TRUE_NOT_FOUND = "None of your delivery tasks are reserved";
	private static final String MSG_PAGE_RESERVED_FALSE_NOT_FOUND = "All your delivery tasks are reserved";
	private static final String MSG_PAGE_DELIVERY_MAN_TASKS_NOT_FOUND = "No delivery task was found";
	
	
	private DeliveryTaskRepository dTaskRepo;
	
	@Autowired
	public DeliveryTaskServiceImpl(DeliveryTaskRepository dTaskRepo){
		this.dTaskRepo = dTaskRepo;
	} 
	
	@Override
	public DeliveryTask create(DeliveryTask dTask) {
		// TODO Auto-generated method stub
		dTask.setCreatedDate(new Date());
		return dTaskRepo.save(dTask);
	}

	@Override
	public DeliveryTask getReference(Long idTask) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = dTaskRepo.getOne(idTask);
		
		return dTask;
	}
	
	@Override
	public DeliveryTask read(Long idTask, String deliveryMan) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = dTaskRepo.findByIdAndDeliveryMan(idTask, deliveryMan);
		if(dTask == null) throw new DeliveryTaskNotFoundException(MSG_NOT_FOUND);
		return dTask;
	}
	
	@Override
	public DeliveryTask read(Long idTask, Long idCreator) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = dTaskRepo.findByIdAndCreatorId(idTask, idCreator);
		if(dTask == null) throw new DeliveryTaskNotFoundException(MSG_NOT_FOUND);
		return dTask;
	}
	
	@Override
	public DeliveryTask read(Long id) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = dTaskRepo.findOne(id);
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
	public PageOf<DeliveryTask> findAll(int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageConfig = new PageRequest(page, size, new Sort(new Sort.Order(Direction.DESC, "createdDate")));
		Page<DeliveryTask> pageDTask = dTaskRepo.findAll(pageConfig);
		PageOf<DeliveryTask> pageOfDeliveryTasks = createPageOf(pageDTask);
		return pageOfDeliveryTasks;
	}
	
	@Override
	public PageOf<DeliveryTask> findPageByUser(Long idUser, int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageConfig = new PageRequest(page, size, new Sort(new Sort.Order(Direction.DESC, "createdDate")));
		Page<DeliveryTask> pageDTask = dTaskRepo.findByCreatorId(idUser, pageConfig);
		if(!pageDTask.hasContent()) throw new DeliveryTaskNotFoundException(MSG_PAGE_NOT_FOUND);
		PageOf<DeliveryTask> pageOfDeliveryTasks = createPageOf(pageDTask);
		return pageOfDeliveryTasks;
	}

	@Override
	public PageOf<DeliveryTask> findCompleted(Long idUser, boolean completed, int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageConfig = new PageRequest(page, size, new Sort(new Sort.Order(Direction.DESC, "createdDate")));
		Page<DeliveryTask> pageDTask = dTaskRepo.findByCreatorIdAndCompleted(idUser, completed, pageConfig);
		
		String msg = completed == true ? MSG_PAGE_COMPLETED_TRUE_NOT_FOUND : MSG_PAGE_COMPLETED_FALSE_NOT_FOUND;
		if(!pageDTask.hasContent()) throw new DeliveryTaskNotFoundException(msg);
		PageOf<DeliveryTask> pageOfDeliveryTasks = createPageOf(pageDTask);
		return pageOfDeliveryTasks;
	}

	@Override
	public PageOf<DeliveryTask> findReserved(Long idUser, boolean reserved, int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageConfig = new PageRequest(page, size, new Sort(new Sort.Order(Direction.DESC, "createdDate")));
		Page<DeliveryTask> pageDTask = dTaskRepo.findByCreatorIdAndReserved(idUser, reserved, pageConfig);
		
		String msg = reserved == true ? MSG_PAGE_RESERVED_TRUE_NOT_FOUND : MSG_PAGE_RESERVED_FALSE_NOT_FOUND;
		if(!pageDTask.hasContent()) throw new DeliveryTaskNotFoundException(msg);
		PageOf<DeliveryTask> pageOfDeliveryTasks = createPageOf(pageDTask);
		return pageOfDeliveryTasks;
	}

	@Override
	public PageOf<DeliveryTask> findByDeliveryMan(String deliveryMan, int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageConfig = new PageRequest(page, size, new Sort(new Sort.Order(Direction.DESC, "createdDate")));
		Page<DeliveryTask> pageDTask = dTaskRepo.findByDeliveryMan(deliveryMan, pageConfig);
		
		if(!pageDTask.hasContent()) throw new DeliveryTaskNotFoundException(MSG_PAGE_DELIVERY_MAN_TASKS_NOT_FOUND);
		PageOf<DeliveryTask> pageOfDeliveryTasks = createPageOf(pageDTask);
		return pageOfDeliveryTasks;
	}
	
	@Override
	public PageOf<DeliveryTask> findDeliveryManAndCompleted(String deliveryMan, boolean completed, int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageConfig = new PageRequest(page, size, new Sort(new Sort.Order(Direction.DESC, "createdDate")));
		Page<DeliveryTask> pageDTask = dTaskRepo.findByDeliveryManAndCompleted(deliveryMan, completed, pageConfig);
		
		if(!pageDTask.hasContent()) throw new DeliveryTaskNotFoundException(MSG_PAGE_DELIVERY_MAN_TASKS_NOT_FOUND);
		// need refactor after by a function that return PageOf<>
		PageOf<DeliveryTask> pageOfDeliveryTasks = createPageOf(pageDTask);
		return pageOfDeliveryTasks;
	}
	
	@Override
	public void switchFinishedStatePoint(Long idPoint,Long idTask, String deliveryMan, String state) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = read(idTask, deliveryMan);
		
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
	public void setReservedTrue(Long idTask, String deliveryMan) {
		// TODO Auto-generated method stub
		DeliveryTask dTask = read(idTask);
		if(!dTask.getReserved()){
			dTask.setDeliveryMan(deliveryMan);
			dTask.setReserved(true);
			update(dTask);
		}else{
			// throw already reserved
			throw new RuntimeException("Delivery task already reserved");
		}
		
	}

	@Override
	public void setReservedFalse(Long idTask, Long idCreator) {
		DeliveryTask dTask = read(idTask, idCreator);
		if(dTask.getReserved()){
			dTask.setDeliveryMan(null);
			dTask.setReserved(false);
			update(dTask);
		}else{
			// throw already reserved
			throw new RuntimeException("Delivery task already not reserved");
		}
		
	}
	@Override
	public void flush(){
		dTaskRepo.flush();
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
	
	private PageOf<DeliveryTask> createPageOf(Page<DeliveryTask> pageDTask){
		return new PageOf<DeliveryTask>(pageDTask.getContent(),
				pageDTask.getNumber(), 
				pageDTask.getNumberOfElements(), 
				pageDTask.getTotalElements(), 
				pageDTask.getTotalPages());
	}
}
