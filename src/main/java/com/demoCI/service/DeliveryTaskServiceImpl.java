/**
 * 
 */
package com.demoCI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.demoCI.model.DeliveryTask;
import com.demoCI.repository.DeliveryTaskRepository;

/**
 * @author Med
 * 12 août 2017
 */
public class DeliveryTaskServiceImpl implements DeliveryTaskService {

	private DeliveryTaskRepository dTaskRepo;
	
	@Autowired
	public DeliveryTaskServiceImpl(DeliveryTaskRepository dTaskRepo){
		this.dTaskRepo = dTaskRepo;
	} 
	
	@Override
	public DeliveryTask create(DeliveryTask dTask) {
		// TODO Auto-generated method stub
		return dTaskRepo.save(dTask);
	}

	@Override
	public DeliveryTask read(Long id) {
		// TODO Auto-generated method stub
		return dTaskRepo.findOne(id);
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
		return dTaskRepo.findByCreatorId(idUser, new PageRequest(page, size));
	}

	@Override
	public Page<DeliveryTask> findCompleted(Long idUser, boolean completed, int page, int size) {
		// TODO Auto-generated method stub
		return dTaskRepo.findByCreatorIdAndCompleted(idUser, completed, new PageRequest(page, size));
	}

	@Override
	public Page<DeliveryTask> findReserved(Long idUser, boolean reserved, int page, int size) {
		// TODO Auto-generated method stub
		return dTaskRepo.findByCreatorIdAndReserved(idUser, reserved, new PageRequest(page, size));
	}

	@Override
	public void switchFinishedPoint(Long idDPoint) {
		// TODO Auto-generated method stub
		
	}

}
