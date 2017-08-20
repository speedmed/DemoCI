/**
 * 
 */
package com.demoCI.service;

import org.springframework.data.domain.Page;

import com.demoCI.model.User;

/**
 * @author Med
 * 12 août 2017
 */
public interface UserService {
	
	public User create(User u);
	public User read(Long id);
	public User getReference(Long id);
	public User update(User u);
	public void delete(Long id);
	public Page<User> findByPage(int page, int size);
	public User findByUsername(String username);
}
