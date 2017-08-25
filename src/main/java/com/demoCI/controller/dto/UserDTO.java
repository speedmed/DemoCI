/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 22 août 2017
 */
public class UserDTO {

	private Long id;
	private String username;
	private String email;
	private String password;
	
	public UserDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	
}
