/**
 * 
 */
package com.demoCI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoCI.model.User;

/**
 * @author Med
 * 12 août 2017
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
