/**
 * 
 */
package com.demoCI.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoCI.model.DeliveryPoint;
import com.demoCI.repository.DeliveryPointRepository;

/**
 * @author Med
 * 12 août 2017
 */
@Service
@Transactional
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
