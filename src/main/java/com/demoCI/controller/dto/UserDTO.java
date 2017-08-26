/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 22 août 2017
 */
// Used to transfert insensitive data
public class UserDTO {

	private Long id;
	private String username;
	private String email;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(String username, String email) {
		super();
		this.username = username;
		this.email = email;
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
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
	
}
