/**
 * 
 */
package com.demoCI.exception;

/**
 * @author Med
 * 22 août 2017
 */
public class DeliveryTaskNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public DeliveryTaskNotFoundException(String message) {
		super(message);
	}

	
	
	
}
