package db;

import domain.Customer;
import domain.Product;
import domain.ProductStateEnum;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import app.MagicStrings;
import db.DbException;

public class SqlDb implements Database {

	
	private static String dbURL = "jdbc:derby:resources/shopDb;create=true";
	private static String productsTableName = "products";
	private static String customersTableName = "customers";
	private static Connection conn = null;
	private static Statement stmt =null;
	
	public SqlDb() throws DbException{
		conn = this.createConnection();
		createTable();
	}
	
	private Connection createConnection() throws DbException{
		try{
			return DriverManager.getConnection(dbURL);
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	private void createTable(){
		try{
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE "+productsTableName+" (id INTEGER PRIMARY KEY, title VARCHAR(20), class VARCHAR(20), state VARCHAR(20))");
			stmt.execute("CREATE TABLE "+customersTableName+" (id INTEGER PRIMARY KEY, firstName VARCHAR(20), lastName VARCHAR(20), address VARCHAR(20), zipCode VARCHAR(4), city VARCHAR(20), emailAddress VARCHAR(50), subscribed BOOLEAN)");
			stmt.close();
		}catch(SQLException e){
			try {
				stmt.close();
			} catch (SQLException e1) {}
		}
		
	}	
	
	public void addProduct(Product p) throws DbException{
			try{
				stmt = conn.createStatement();
				stmt.execute("INSERT INTO "+ productsTableName + " VALUES (" + p.getId() + ",'" + p.getTitle() + "','" + p.getClass().getName() + "','" + p.getCurrentState() + "')");
				stmt.close();
			}catch(SQLException e){
				try {
					stmt.close();
				} catch (SQLException e1) {}
				throw new DbException(MagicStrings.EXISTINGIDINDB.getError()+p.getId());
			}
			
	}

	public Product getProduct(int id) throws DbException{
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM " + productsTableName + " WHERE id="+id);
			
			set.next();
			int i = Integer.parseInt(set.getString("id"));
			String name = set.getString("title");
			String classStr = set.getString("class");
			String stateStr = set.getString("state");
			stmt.close();
			
			Class<?> clazz = Class.forName(classStr);
			Constructor<?> ctor = clazz.getConstructor(int.class,String.class,ProductStateEnum.class);
			return (Product)ctor.newInstance((i),name,ProductStateEnum.valueOf(stateStr));	
			
		}catch(Exception e){
			try{
				stmt.close();
			}catch (Exception e1) {}
			throw new DbException(MagicStrings.PRODUCTNOTFOUNDINDB.getError()+id);
		}
	}

	public void deleteProduct(int id) throws DbException {
		try{
			stmt = conn.createStatement();
			stmt.execute("DELETE FROM "+productsTableName+" WHERE id="+id);
			stmt.close();
		}catch(SQLException e){
			try{
				stmt.close();
			}catch (Exception e1) {}
			
			throw new DbException(MagicStrings.NOTDELETABLE.getError());
		}		
	}

	public void updateProduct(Product p) throws DbException {
		try {
			stmt = conn.createStatement();
			stmt.execute("UPDATE " + productsTableName + 
				    " SET state='"+p.getCurrentState()+
				    "' WHERE id=" +p.getId() );
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {}
			System.out.println(e.getMessage());
			throw new DbException("Could not update this product");
		}
		
	}

	public void addCustomer(Customer c) throws DbException {
		try {
			stmt = conn.createStatement();
			stmt.execute("INSERT INTO "+ customersTableName + " VALUES (" + c.getId() + ",'" + c.getFirstName() + "','" + c.getLastName() + "','" + c.getAddress() + "','" + c.getZipCode() + "','" + c.getCity() + "','" + c.getEmailAddress() + "','" + c.isSubscribed() + "')");
			stmt.close();
			
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {}
			throw new DbException();
		}
		
	}

	public Customer getCustomer(int id) {
		Customer c = null;
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM "+customersTableName+" WHERE id="+id);
			set.next();
			int i = Integer.parseInt(set.getString("id"));
			String firstName = set.getString("firstName");
			String lastName = set.getString("lastName");
			String address = set.getString("address");
			String zipCode = set.getString("zipCode");
			String city = set.getString("city");
			String emailAddress = set.getString("emailAddress");
			boolean subscribed = set.getString("subscribed").toLowerCase().equals("true");
			
			c = new Customer(i, firstName, lastName, address, zipCode, city, emailAddress, subscribed);
			
			stmt.close();
		}catch (Exception e) {
			try {
				stmt.close();
			} catch (SQLException e1) {}
			
		}
		
		return c;
	}

	public void updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getAllSubscribedCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Product getLastAddedProduct() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
