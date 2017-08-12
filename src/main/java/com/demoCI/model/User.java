package com.demoCI.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Med
 * 9 août 2017
 */
@Entity
@Table(name="users", indexes={@Index(name="idx_user1", columnList="username", unique=true), 
								@Index(name="idx_user2", columnList="email", unique=true)})
public class User extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private String password;
	private Boolean enabled = true;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "users_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"),
				uniqueConstraints = { @UniqueConstraint(columnNames={"user_id","role_id"}) })
	private Set<Role> roles = new HashSet<Role>();
//	private List<DeliveryTask> deliveryTasks;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public void addRole(Role role){
		this.roles.add(role);
		role.getUsers().add(this);
	}
	public void removeRole(Role role){
		this.roles.remove(role);
		role.getUsers().remove(this);
	}
	
	public void remove(){
		this.roles.clear();
	}
}
