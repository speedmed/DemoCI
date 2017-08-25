/**
 * 
 */
package com.demoCI.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Med
 * 9 août 2017
 */
@Entity
@Table(name="delivery_points")
public class DeliveryPoint extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	private String description;
	// No or Yes or in Progress
	private String finished = "No";
	private Double longitude;
	private Double latitude;
	
	//owning side
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="deliveryTask_fk")
	private DeliveryTask deliveryTask;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	public DeliveryTask getDeliveryTask() {
		return deliveryTask;
	}

	public void setDeliveryTask(DeliveryTask deliveryTask) {
		this.deliveryTask = deliveryTask;
	}

	public DeliveryPoint(String description, String finished, Double longitude,
			Double latitude) {
		super();
		this.description = description;
		this.finished = finished;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public DeliveryPoint() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DeliveryPoint [description=" + description + ", finished=" + finished + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", deliveryTaskId=" + deliveryTask.getId() + ", getId()=" + getId() + "]";
	}	
	
}
