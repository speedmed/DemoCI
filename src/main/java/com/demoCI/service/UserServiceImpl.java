/**
 * 
 */
package com.demoCI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demoCI.model.User;
import com.demoCI.repository.UserRepository;

/**
 * @author Med
 * 12 août 2017
 */
@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepo;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo){
		
		this.userRepo = userRepo;
	}
	
	@Override
	public User create(User u) {
		// TODO Auto-generated method stub
		return userRepo.save(u);
	}

	@Override
	public User read(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findOne(id);
	}

	@Override
	public User update(User u) {
		// TODO Auto-generated method stub
		User user = read(u.getId());
		u.setPassword(user.getPassword());
		return userRepo.save(u);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userRepo.delete(id);
	}

	@Override
	public Page<User> findByPage(int page, int size) {
		// TODO Auto-generated method stub
		return userRepo.findAll(new PageRequest(page, size));
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.getByUsername(username);
	}

}
