package org.sgudipat.demo.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

//1-M mapping: product can have many categories. Hibernate creates a table prod_cat which has the pk of both the tables.
// M-M mapping
@Entity
public class Product {
	@Id
	private int productId;
	private String productName;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinTable(joinColumns=@JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns=@JoinColumn(name="CAT_ID"))
	
	//@ManyToMany
	private Collection<Category> listOfCategories = new ArrayList<>();
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Collection<Category> getListOfCategories() {
		return listOfCategories;
	}
	public void setListOfCategories(Collection<Category> listOfCategories) {
		this.listOfCategories = listOfCategories;
	}
	

}
