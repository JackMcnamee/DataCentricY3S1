package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StoreController {
	DAO dao;
	ArrayList<Store> stores;
	
	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Store> getStores(){
		return stores;
	}
	
	public void setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}
	
	// show all stores
	public String loadStores() {
		// output to console
		System.out.println("In loadStores()");
		try {
			stores = dao.loadStores();
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database"); // error message
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
	
	// add store
	public String addStore(Store s) {
		// output to console
		System.out.println("In addStore()");
		System.out.println("Added Store Details: Id: " + s.getId() + ", Name: " + s.getName() + ", Founded: " + s.getFounded());
		try {
			dao.addStore(s);
			return "list_stores";
		}catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store "+ s.getName() + " already exists"); // error message
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	};
	
	// delete store that name is equal to
	public void deleteStore(String name) {
		// output to console
		System.out.println("Deleted Store: " + name);
		try {
			dao.deleteStore(name);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Store "+ name + " has not been deleted from MySQLDB, it contains products"); // error message
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
	}
	
}
