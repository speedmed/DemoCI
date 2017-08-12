/**
 * 
 */
package com.demoCI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoCI.model.DeliveryPoint;

/**
 * @author Med
 * 12 août 2017
 */
public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, Long> {

}
