/**
 * 
 */
package com.demoCI.controller.dto;

/**
 * @author Med
 * 19 août 2017
 */
public class DeliveryPointDTO {
	
	private Long id;
	private String description;
	private String finished;
	private Double longitude;
	private Double latitude;
	
	private Long deliveryTaskId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getDeliveryTaskId() {
		return deliveryTaskId;
	}

	public void setDeliveryTaskId(Long deliveryTaskId) {
		this.deliveryTaskId = deliveryTaskId;
	}

	public DeliveryPointDTO() {
		super();
	}

	public DeliveryPointDTO(String description, String finished, Double longitude, Double latitude,
			Long deliveryTaskId) {
		super();
		this.description = description;
		this.finished = finished;
		this.longitude = longitude;
		this.latitude = latitude;
		this.deliveryTaskId = deliveryTaskId;
	}

	@Override
	public String toString() {
		return "DeliveryPointDTO [id=" + id + ", description=" + description + ", finished=" + finished + ", longitude="
				+ longitude + ", latitude=" + latitude + ", deliveryTaskId=" + deliveryTaskId + "]";
	}
	
}
