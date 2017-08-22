/**
 * 
 */
package com.demoCI.exception;

/**
 * @author Med
 * 22 août 2017
 */
public class UserNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	

}
