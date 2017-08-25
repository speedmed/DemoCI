/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 19 août 2017
 */
public class ReservedTaskDTO {
	
	private Long deliveryTaskId;
	
	public ReservedTaskDTO() {
		super();
	}
	
	public ReservedTaskDTO(Long deliveryTaskId) {
		super();
		this.deliveryTaskId = deliveryTaskId;
	}
	
	public Long getDeliveryTaskId() {
		return deliveryTaskId;
	}
	public void setDeliveryTaskId(Long deliveryTaskId) {
		this.deliveryTaskId = deliveryTaskId;
	}
}
