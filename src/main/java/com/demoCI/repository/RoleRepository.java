/**
 * 
 */
package com.demoCI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoCI.model.Role;

/**
 * @author Med
 * 12 août 2017
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role getByRoleName(String roleName);
}
