/**
 * 
 */
package com.demoCI.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Med
 * 19 août 2017
 */
public class DeliveryTaskDTO {

	private Long id;
	private String taskName;
	private Date createdDate;
	private Date endDate;
	private Boolean completed;;
	private Float progress;
	private Boolean reserved;
	private String deliveryMan;
	private Long creatorId;
	
	private List<DeliveryPointDTO> deliveryPoints = new ArrayList<DeliveryPointDTO>();
	
	
	public DeliveryTaskDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Float getProgress() {
		return progress;
	}

	public void setProgress(Float progress) {
		this.progress = progress;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public String getDeliveryMan() {
		return deliveryMan;
	}

	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}
	
	public List<DeliveryPointDTO> getDeliveryPoints() {
		return deliveryPoints;
	}

	public void setDeliveryPoints(List<DeliveryPointDTO> deliveryPoints) {
		this.deliveryPoints = deliveryPoints;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public String toString() {
		return "DeliveryTaskDTO [id=" + id + ", taskName=" + taskName + ", createdDate=" + createdDate + ", endDate="
				+ endDate + ", completed=" + completed + ", progress=" + progress + ", reserved=" + reserved
				+ ", deliveryMan=" + deliveryMan + ", deliveryPoints=" + deliveryPoints + "]";
	}
	
	
}
