/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 19 août 2017
 */
public class reservedTaskDTO {
	
	private Long deliveryTaskId;
	private Long deliveryManId;
	private Boolean reserved;
	
	public reservedTaskDTO() {
		super();
	}
	public Long getDeliveryManId() {
		return deliveryManId;
	}
	public void setDeliveryManId(Long deliveryManId) {
		this.deliveryManId = deliveryManId;
	}
	
	public Long getDeliveryTaskId() {
		return deliveryTaskId;
	}
	public void setDeliveryTaskId(Long deliveryTaskId) {
		this.deliveryTaskId = deliveryTaskId;
	}
	public Boolean getReserved() {
		return reserved;
	}
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
}
