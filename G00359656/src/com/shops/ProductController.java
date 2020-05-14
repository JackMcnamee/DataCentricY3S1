package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProductController {
	
	DAO dao;
	ArrayList<Product> products;
	
	public ProductController() {
		super();
		try {
			dao=new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setProducts(new ArrayList<Product>());
	}

	public DAO getDao() {
		return dao;
	}
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> product) {
		this.products = product;
	}
	
	// show all products
	public String loadProducts() {
		// output to console
		System.out.println("In loadProducts()");
		try {
			products = dao.loadProducts();
		} catch (SQLException e) { 
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database"); // error message
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	
	// delete product pid is equal to
	public void delete(int pid) {
		// output to console
		System.out.println("Deleted product " + pid);
		try {
			dao.delete(pid);
		} catch (SQLException e) { 
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database"); // error message
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// show products of store selected
	public String showProducts(Store store) {
		// output to console
		System.out.println("In showProducts()");
		try {
			setProducts(getDao().getProducts(store));
		} catch (SQLException e) { 
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database"); // error message
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show_products";
	};
	

}
