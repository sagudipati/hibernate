package org.sgudipat.demo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//1-1 mapping : One user will have One order

@Entity
public class OrderDetails {
	
	@Id @GeneratedValue
	private int orderKey;
	private Date orderDate;
	private String orderedBy;
	
	@OneToOne
	@JoinColumn(name="USER_ID")
	private UserDetails userDetails;
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public int getOrderKey() {
		return orderKey;
	}
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderedBy() {
		return orderedBy;
	}
	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}
	
	
	
}
