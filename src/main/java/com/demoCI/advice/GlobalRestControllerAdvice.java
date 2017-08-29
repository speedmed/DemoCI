/**
 * 
 */
package com.demoCI.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demoCI.exception.DeliveryTaskNotFoundException;
import com.demoCI.exception.UserNotFoundException;

/**
 * @author Med
 * 25 août 2017
 */
@RestControllerAdvice
public class GlobalRestControllerAdvice {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseMsg> userNotFound(UserNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg(ex.getMessage()));
	}
	
	@ExceptionHandler(DeliveryTaskNotFoundException.class)
	public ResponseEntity<ResponseMsg> deliveryTaskNotFound(DeliveryTaskNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg(ex.getMessage()));
	}
}
