/**
 * 
 */
package com.demoCI.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demoCI.exception.UserNotFoundException;
import com.demoCI.model.User;
import com.demoCI.repository.UserRepository;

/**
 * @author Med
 * 12 août 2017
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final String MSG_NOT_FOUND = "User not found";
	
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
		User u = userRepo.findOne(id);
		if(u == null) throw new UserNotFoundException(MSG_NOT_FOUND);
		return u;
	}

	@Override
	public User getReference(Long id) {
		// TODO Auto-generated method stub
		User u = userRepo.getOne(id);
		if(u == null) throw new UserNotFoundException(MSG_NOT_FOUND);
		return u; 
	}
	
	@Override
	public User update(User u) {
		// TODO Auto-generated method stub
		User user = read(u.getId());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		return userRepo.save(u);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userRepo.delete(id);
	}

	@Override
	public PageOf<User> findByPage(int page, int size) {
		// TODO Auto-generated method stub
		Page<User> pageUser = userRepo.findAll(new PageRequest(page, size));
		PageOf<User> pageOfUsers = new PageOf<User>(pageUser.getContent(),
				pageUser.getNumber(), 
				pageUser.getNumberOfElements(), 
				pageUser.getTotalElements(), 
				pageUser.getTotalPages());
		return pageOfUsers;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User u = userRepo.getByUsername(username);
		if(u == null) throw new UserNotFoundException(MSG_NOT_FOUND);
		return u;
	}


}
