package org.sgudipat.demo.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {

	@Id
	private int catId;
	private String catName;
	//@ManyToMany(mappedBy="listOfCategories")
	//private Collection<Product> listOfProducts = new ArrayList<>();
	
	
	/*public Collection<Product> getListOfProducts() {
		return listOfProducts;
	}
	public void setListOfProducts(Collection<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}*/
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	
}
