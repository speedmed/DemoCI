/**
 * 
 */
package com.demoCI.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoCI.model.Role;
import com.demoCI.repository.RoleRepository;

/**
 * @author Med
 * 12 août 2017
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	private RoleRepository roleRepo;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepo){
		this.roleRepo = roleRepo;
	}
	
	@Override
	public Role create(Role r) {
		// TODO Auto-generated method stub
		return roleRepo.save(r);
	}

	@Override
	public Role read(Long id) {
		// TODO Auto-generated method stub
		return roleRepo.findOne(id);
	}

	@Override
	public Role update(Role r) {
		// TODO Auto-generated method stub
		return roleRepo.save(r);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		roleRepo.delete(id);
	}

	@Override
	public Role findByName(String roleName) {
		// TODO Auto-generated method stub
		return roleRepo.getByRoleName(roleName);
	}

}
