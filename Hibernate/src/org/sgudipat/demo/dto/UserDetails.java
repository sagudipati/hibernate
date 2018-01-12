package org.sgudipat.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@NamedQuery(name="UserDetails.byId", query="from UserDetails where userId =?")
@NamedNativeQuery(name="UserDetails.byName", query="select * from USER_DETAILS where userName =?",resultClass=UserDetails.class) //not HQL query
@Table(name="USER_DETAILS")
public class UserDetails implements Serializable {
	
	@Id
	private int userId; //If you have the pk as embedded then use EmbeddedId annotation. You cannot use both ID and Embedded annotations
	private String userName;
	@Temporal(TemporalType.DATE)
	private Date joinedDate;
	//including another object
	@Embedded
	private Address address;
	
	@Embedded
	@AttributeOverrides({
	@AttributeOverride(name="street", column = @Column(name="Office_Street")),
	@AttributeOverride(name="city", column = @Column(name="Office_City")),
	@AttributeOverride(name="pincode", column = @Column(name="Office_pincode")),
	@AttributeOverride(name="state", column = @Column(name="Office_state"))})
	//change the column name if there is a clash
	private Address officeAddress;
	
	//this annotation will help not to include the variable in the database
	@Transient
	@Lob //lob is for long object which can exceed 255 characters
	private String description;
	
	//when you use a data structure
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="USER_PHONE", joinColumns= @JoinColumn(name="USER_ID"))
	@GenericGenerator(name="hilo-gne",strategy="hilo")
	@CollectionId(columns= {@Column(name="PHONE_ID")}, generator="hilo-gen", type= @Type(type="int"))
	//to change the name of the table
	//private Set<Phone> listOfPhones = new HashSet<>(); //changin to arraylist to generated an index(for pk for the table). Set doesnt have indexes so using arraylist.
	private Collection<Phone> listOfPhones = new ArrayList<>();
	
	
	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	public Collection<Phone> getListOfPhones() {
		return listOfPhones;
	}
	public void setListOfPhones(Collection<Phone> listOfPhones) {
		this.listOfPhones = listOfPhones;
	}
	

}
