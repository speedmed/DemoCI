/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 25 août 2017
 */
//used to login user
public class UserLoginDTO {
	
	private String username;
	private String password;
	
	public UserLoginDTO() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + "]";
	}
}
