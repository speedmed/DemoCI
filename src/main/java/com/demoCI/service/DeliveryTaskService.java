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
	//load a reference (proxy) for DeliveryTask without loading all the object.
	public DeliveryTask getReference(Long id);
	public DeliveryTask read(Long id, Long idCreator);
	public DeliveryTask update(DeliveryTask dTask);
	public void delete(Long id);
	public Page<DeliveryTask> findByPage(Long idUser, int page, int size);
	public Page<DeliveryTask> findCompleted(Long idUser, boolean completed, int page, int size);
	public Page<DeliveryTask> findReserved(Long idUser, boolean reserved, int page, int size);
	//switch finished state for the DeliveryPoint
	public void switchFinishedStatePoint(Long idPoint,Long id, Long idCreator, String state);
	//switch reserved property
	public void setReserved(Long idTask, Long idDeliveryMan, Boolean reserved);
	
}
