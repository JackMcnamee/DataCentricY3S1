package com.shops;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mongodb.*;

@ManagedBean
@SessionScoped
public class OfficeController {

	MongoDAO mongo;
	ArrayList<Office> offices;
	
	public OfficeController() {
		super();
		try {
			mongo = new MongoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Office> getOffices() {
		return offices;
	}

	public void setOffices(ArrayList<Office> offices) {
		this.offices = offices;
	}
	
	// show all offices
	public String loadOffices() {
		// output to console
		System.out.println("In loadOffices()");
		try {
			offices = mongo.loadOffices();
		} catch (MongoException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database"); // error message
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
	
	// add office
	public String addOffice(Office office) {
		// output to console
		System.out.println("In addOffice()");
		System.out.println("Added Office Details: Id: " + office.get_id() + ", Location: " + office.getLocation());
			try {
				mongo.addOffice(office);
				return "list_offices";
			} catch (MongoWriteException e) {
				FacesMessage message = new FacesMessage("Error: Store ID: "+ office.get_id() + " already has location"); // error message
				FacesContext.getCurrentInstance().addMessage(null, message);
				e.printStackTrace();
			} catch (MongoException e) {
				FacesMessage message = new FacesMessage("Error: Store ID: "+ office.get_id() + " does not exist"); // error message
				FacesContext.getCurrentInstance().addMessage(null, message);
				e.printStackTrace();
			}
		return null;
	};
}
