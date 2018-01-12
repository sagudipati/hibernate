package org.sgudipat.demo.dto;

import javax.persistence.Embeddable;

@Embeddable
public class Phone {
	
	private String office;
	private String home;
	private String mobile;
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
