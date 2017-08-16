/**
 * 
 */
package com.demoCI.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Med
 * 9 août 2017
 */
@Entity
@Table(name="delivery_tasks")
public class DeliveryTask extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String taskName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	private Boolean completed = false;
	@Column(precision=5, scale=2)
	private Float progress;
	//is the delivery task picked by a delivery man ?
	private Boolean reserved = false;
	
	private String deliveryMan;
	//creator of delivery Task
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator_fk", nullable = false)
	private User creator;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval= true, fetch = FetchType.LAZY, mappedBy = "deliveryTask")
	private List<DeliveryPoint> deliveryPoints = new ArrayList<DeliveryPoint>();
	

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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<DeliveryPoint> getDeliveryPoints() {
		return deliveryPoints;
	}

	public void setDeliveryPoints(List<DeliveryPoint> deliveryPoints) {
		this.deliveryPoints = deliveryPoints;
	}

	public DeliveryTask(String taskName, Boolean completed, Boolean reserved, User creator) {
		super();
		this.taskName = taskName;
		this.completed = completed;
		this.reserved = reserved;
		this.creator = creator;
	}

	public DeliveryTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void addDeliveryPoint(DeliveryPoint deliveryPoint){
		this.deliveryPoints.add(deliveryPoint);
		deliveryPoint.setDeliveryTask(this);
	}
	
	public void removeDeliveryPoint(DeliveryPoint deliveryPoint){
		this.deliveryPoints.remove(deliveryPoint);
		deliveryPoint.setDeliveryTask(null);
	}
	
	
}
