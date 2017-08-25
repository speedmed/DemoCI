package com.demoCI.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Med
 * 9 août 2017
 */
@Entity
@Table(name="roles")
public class Role extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, unique=true)
	private String roleName;
	
	@ManyToMany(mappedBy="roles", fetch=FetchType.LAZY)
	private Set<User> users = new HashSet<User>();

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Role(String roleName) {
		super();
		this.roleName = "ROLE_"+roleName;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
