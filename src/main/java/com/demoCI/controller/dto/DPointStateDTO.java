/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 24 août 2017
 */
//DeliveryPoint State
public class DPointStateDTO {

	Long idPoint;
	Long idTask;
	String state;
	public DPointStateDTO(Long idPoint, Long idTask, String state) {
		super();
		this.idPoint = idPoint;
		this.idTask = idTask;
		this.state = state;
	}
	public DPointStateDTO() {
		super();
	}
	public Long getIdPoint() {
		return idPoint;
	}
	public void setIdPoint(Long idPoint) {
		this.idPoint = idPoint;
	}
	public Long getIdTask() {
		return idTask;
	}
	public void setIdTask(Long idTask) {
		this.idTask = idTask;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
