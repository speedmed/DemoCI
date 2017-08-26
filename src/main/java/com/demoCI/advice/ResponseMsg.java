/**
 * 
 */
package com.demoCI.advice;

/**
 * @author Med
 * 26 août 2017
 */
public class ResponseMsg {

	private String message;
	 
	public ResponseMsg(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
}
