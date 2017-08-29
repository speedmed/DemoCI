/**
 * 
 */
package com.demoCI.service;

import com.demoCI.model.DeliveryTask;

/**
 * @author Med
 * 12 août 2017
 */
public interface DeliveryTaskService {

	public DeliveryTask create(DeliveryTask dTask);
	//load a reference (proxy) for DeliveryTask without loading all the object.
	public DeliveryTask getReference(Long idTask);
	public DeliveryTask read(Long idTask);
	public DeliveryTask read(Long idTask, String deliveryMan);
	public DeliveryTask read(Long idTask, Long idCreator);
	public DeliveryTask update(DeliveryTask dTask);
	public void delete(Long id);
	//switch finished state for the DeliveryPoint, deliveryMan will be fetched from JWT
	public void switchFinishedStatePoint(Long idPoint,Long idtask, String deliveryMan, String state);
	//switch reserved property, deliveryMan will be fetched from JWT
	public void setReservedTrue(Long idTask, String deliveryMan);
	public void setReservedFalse(Long idTask, Long idCreator);
		
	public void flush();
	
	public PageOf<DeliveryTask> findAll(int page, int size);
	public PageOf<DeliveryTask> findPageByUser(Long idUser, int page, int size);
	public PageOf<DeliveryTask> findCompleted(Long idUser, boolean completed, int page, int size);
	public PageOf<DeliveryTask> findReserved(Long idUser, boolean reserved, int page, int size);
	public PageOf<DeliveryTask> findByDeliveryMan(String deliveryMan, int page, int size);
	public PageOf<DeliveryTask> findDeliveryManAndCompleted(String deliveryMan, boolean completed, int page, int size);
	
	
}
