package com.shops;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	private DataSource mysqlDS;
	
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	public DataSource getMysqlDS() {
		return mysqlDS;
	}

	public void setMysqlDS(DataSource mysqlDS) {
		this.mysqlDS = mysqlDS;
	}
	

	// load all store details from store
	public ArrayList<Store> loadStores() throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from store"; // shows everything from store

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Store> stores = new ArrayList<Store>();

		// process result set
		while (myRs.next()) {
			Store s = new Store();
			s.setId(myRs.getInt("id"));
			s.setName(myRs.getString("name"));
			s.setFounded(myRs.getString("founded"));
			stores.add(s);
		}
		
		return stores;
		
	}
	
	// add a store
	public void addStore(Store store) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into store values (?, ?, ?)"; // adds values to store
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, store.getId());
		myStmt.setString(2, store.getName());
		myStmt.setString(3, store.getFounded());
		myStmt.execute();	
	}
	
	// delete a store
	public void deleteStore(String name) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from store where id = ?"; // remove store from database that id is equal to
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, name);
		myStmt.execute();			
	}
	
	// find products of selected store
	public ArrayList<Product> getProducts(Store store) throws SQLException{
		Connection myConn = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from product where sid=?"; // select all from product that sid is equal to

		PreparedStatement statement = myConn.prepareStatement(sql);
		statement.setInt(1, store.getId());

		myRs = statement.executeQuery();
				
		ArrayList<Product> products = new ArrayList<Product>();

		// process result set
		while (myRs.next()) {
			int pid = myRs.getInt("pid");
			int sid = myRs.getInt("sid");
			String prodName = myRs.getString("prodName");
			double price = myRs.getDouble("price");
			
			Product p = new Product(pid, sid, prodName, price, store);
			products.add(p);
			
		}
		
		return products;
	}
	
	// load all product details from product
	public ArrayList<Product> loadProducts() throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from product"; // show everything from product

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Product> products = new ArrayList<Product>();

		// process result set
		while (myRs.next()) {
			Product p = new Product();
			p.setPid(myRs.getInt("pid"));
			p.setSid(myRs.getInt("sid"));
			p.setProdName(myRs.getString("prodName"));
			p.setPrice(myRs.getDouble("price"));
			products.add(p);
		}
		
		return products;
	}
	
	// delete product selected by user
	public void delete(int pid) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from product where pid = ?"; // remove product from database that pid is equal to
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, pid);
		myStmt.execute();			
	}
}
