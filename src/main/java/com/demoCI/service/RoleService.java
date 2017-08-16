/**
 * 
 */
package com.demoCI.service;

import com.demoCI.model.Role;

/**
 * @author Med
 * 12 août 2017
 */
public interface RoleService {

	public Role create(Role r);
	public Role read(Long id);
	public Role update(Role r);
	public void delete(Long id);
	public Role findByName(String roleName);
}
