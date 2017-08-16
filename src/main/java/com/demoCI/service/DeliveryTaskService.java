/**
 * 
 */
package com.demoCI.service;

import org.springframework.data.domain.Page;

import com.demoCI.model.DeliveryTask;

/**
 * @author Med
 * 12 août 2017
 */
public interface DeliveryTaskService {

	public DeliveryTask create(DeliveryTask dTask);
	public DeliveryTask read(Long id);
	public DeliveryTask update(DeliveryTask dTask);
	public void delete(Long id);
	public Page<DeliveryTask> findByPage(Long idUser, int page, int size);
	public Page<DeliveryTask> findCompleted(Long idUser, boolean completed, int page, int size);
	public Page<DeliveryTask> findReserved(Long idUser, boolean reserved, int page, int size);
	public void switchFinishedPoint(Long idDPoint);
	
}
