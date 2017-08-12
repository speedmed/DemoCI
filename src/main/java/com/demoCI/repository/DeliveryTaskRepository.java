/**
 * 
 */
package com.demoCI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoCI.model.DeliveryTask;

/**
 * @author Med
 * 12 août 2017
 */
public interface DeliveryTaskRepository extends JpaRepository<DeliveryTask, Long> {

}
