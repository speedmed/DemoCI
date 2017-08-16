/**
 * 
 */
package com.demoCI.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.demoCI.model.DeliveryPoint;
import com.demoCI.repository.DeliveryPointRepository;

/**
 * @author Med
 * 12 août 2017
 */
public class DeliveryPointServiceImpl implements DeliveryPointService {
	
	private DeliveryPointRepository dPointRepo;
	
	@Autowired
	public DeliveryPointServiceImpl(DeliveryPointRepository dPointRepo){
		
		this.dPointRepo = dPointRepo;
	}

	@Override
	public DeliveryPoint read(Long id) {
		// TODO Auto-generated method stub
		return dPointRepo.findOne(id);
	}

}
