/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 25 août 2017
 */
// used to register and to update email by providing the password
public class UserRegisterDTO extends UserDTO{
	
	private String oldPassword;
	private String password;
	
	public UserRegisterDTO() {
		super();
	}

	public UserRegisterDTO(String username, String email, String oldPassword, String password) {
		super(username, email);
		this.oldPassword = oldPassword;
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
