package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
	int pid;
	int sid;
	String prodName;
	double price;
	Store store;
	
	public Product() {
		this.store = new Store();
	}
	
	public Product(int pid, int sid, String prodName, double price, Store store) {
		super();
		this.pid = pid;
		this.sid = sid;
		this.prodName = prodName;
		this.price = price;
		this.store = store;
	}
	
	// getters and setters
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}

}
