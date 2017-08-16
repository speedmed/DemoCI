/**
 * 
 */
package com.demoCI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demoCI.model.DeliveryTask;

/**
 * @author Med
 * 12 août 2017
 */
public interface DeliveryTaskRepository extends JpaRepository<DeliveryTask, Long> {

	public Page<DeliveryTask> findByCreatorId(Long idUser, Pageable page);
	public Page<DeliveryTask> findByCreatorIdAndCompleted(Long idUser, boolean completed, Pageable page);
	public Page<DeliveryTask> findByCreatorIdAndReserved(Long idUser, boolean reserved, Pageable page);
}
