package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Office {
	private int _id;
	String location;
	
	public Office() { }
	
	public Office(int _id, String location) {
		super();
		this._id = _id;
		this.location = location;
	}
	
	// getters and setters
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Office [_id=" + _id + ", location=" + location + "]";
	}
	
}
