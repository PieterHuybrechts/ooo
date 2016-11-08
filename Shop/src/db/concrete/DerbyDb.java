package db.concrete;

import domain.Customer;
import domain.DomainException;
import domain.products.Product;
import domain.products.ProductFactory;
import domain.products.producstates.ProductStateEnum;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.MagicStrings;
import db.Database;
import db.DbException;

public class DerbyDb implements Database {

	private static String productsTableName = "products";
	private static String customersTableName = "customers";
	private static Connection conn = null;
	private static Statement stmt =null;
	
	public DerbyDb(String url) throws DbException{
		String dbUrl = String.format("jdbc:derby:%s;create=true",url+"\\derby");
		conn = this.createConnection(dbUrl);
		createTables();
	}
	
	private Connection createConnection(String url) throws DbException{
		try{
			return DriverManager.getConnection(url);
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	private void createTables(){
		try{
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE "+productsTableName+" (id INTEGER PRIMARY KEY, title VARCHAR(20) NOT NULL, class VARCHAR(20) NOT NULL, state VARCHAR(20) NOT NULL, time_stamp TIMESTAMP NOT NULL)");
			stmt.close();			
		}catch(SQLException e){
			try {
				stmt.close();
			} catch (SQLException e1) {}
		}
		
		try{
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE "+customersTableName+" (id INTEGER PRIMARY KEY, first_name VARCHAR(20), last_name VARCHAR(20), address VARCHAR(20), zip_code VARCHAR(4), city VARCHAR(20), email_address VARCHAR(50), subscribed BOOLEAN)");
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
				stmt.execute("INSERT INTO "+ productsTableName + " VALUES (" + p.getId() + ",'" + p.getTitle() + "','" + p.getClass().getName() + "','" + p.getCurrentState() + "', CURRENT_TIMESTAMP)");
				stmt.close();
			}catch(SQLException e){
				try {
					stmt.close();
				} catch (SQLException e1) {}
				throw new DbException(MagicStrings.PRODUCTEXISTINGINDB.getError()+p.getId());
			}
	}

	public Product getProduct(int id) throws DbException{
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM " + productsTableName + " WHERE id="+id);
			
			set.next();
			int i = Integer.parseInt(set.getString("id"));
			String title = set.getString("title");
			String className = set.getString("class");
			ProductStateEnum state = ProductStateEnum.valueOf(set.getString("state"));
			stmt.close();
			
			return ProductFactory.createProduct(className, i, title, state);
			
		}catch(Exception e){
			try{
				stmt.close();
			}catch (Exception e1) {}
			throw new DbException(MagicStrings.PRODUCTNOTFOUNDINDB.getError()+id);
		}
	}

	@Override
	public List<Product> getAllProducts() throws DbException {
		List<Product> products = new ArrayList<Product>();
		
		try{
			stmt = conn.createStatement();
			
			ResultSet set = stmt.executeQuery("SELECT * FROM "+productsTableName);
			while(set.next()){
				int i = Integer.parseInt(set.getString("id"));
				String title = set.getString("title");
				String className = set.getString("class");
				ProductStateEnum state = ProductStateEnum.valueOf(set.getString("state"));
				products.add(ProductFactory.createProduct(className, i, title, state));
			}
			stmt.close();
		}catch(SQLException | DomainException e){
			try {
				stmt.close();
			} catch (SQLException e1) {}
			
			throw new DbException(e.getMessage());
		}
		
		return products;
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
			
			throw new DbException(MagicStrings.PRODUCTNOTDELETABLEFROMDB.getError()+id);
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
			throw new DbException(MagicStrings.CUSTOMEREXISTINGINDB.getError()+c.getId());
		}
		
	}

	public Customer getCustomer(int id) throws DbException {
		Customer c = null;
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM "+customersTableName+" WHERE id="+id);
			while(set.next()){
				int i = Integer.parseInt(set.getString("id"));
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String address = set.getString("address");
				String zipCode = set.getString("zip_code");
				String city = set.getString("city");
				String emailAddress = set.getString("email_address");
				boolean subscribed = set.getString("subscribed").toLowerCase().equals("true");
				
				c = new Customer(i, firstName, lastName, address, zipCode, city, emailAddress, subscribed);
			}
			
			stmt.close();
		}catch (Exception e) {
			try {
				stmt.close();
			} catch (SQLException e1) {}
			throw new DbException(MagicStrings.CUSTOMERNOTFOUNDINDB.getError()+id);
		}
		
		return c;
	}

	public void deleteCustomer(int id) throws DbException {
		try {
			stmt=conn.createStatement();
			stmt.execute("DELETE FROM " +customersTableName+" WHERE id="+id);
			stmt.close();
		} catch (SQLException e) {
			try{
				stmt.close();
			}catch(Exception e1){}
			
			throw new DbException("Couldn't delete the customer");
		}
	}
	
	public List<Customer> getAllCustomers() throws DbException {
		List<Customer> customers = new ArrayList<Customer>();
		
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM "+customersTableName+ " WHERE subscribed='true'");
			while(set.next()){
				int i = Integer.parseInt(set.getString("id"));
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String address = set.getString("address");
				String zipCode = set.getString("zip_code");
				String city = set.getString("city");
				String emailAddress = set.getString("email_address");
				boolean subscribed = set.getString("subscribed").toLowerCase().equals("true");
				
				customers.add(new Customer(i, firstName, lastName, address, zipCode, city, emailAddress, subscribed));
			}
			stmt.close();
		}catch (Exception e){
			try {
				stmt.close();
			} catch (SQLException e1) {}
			
			throw new DbException("Couldn't get all customers.");
		}
		
		return customers;
	}

	public List<Customer> getAllSubscribedCustomers() throws DbException {
		List<Customer> customers = new ArrayList<Customer>();
		
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM "+customersTableName+ " WHERE subscribed='true'");
			while(set.next()){
				int i = Integer.parseInt(set.getString("id"));
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String address = set.getString("address");
				String zipCode = set.getString("zip_code");
				String city = set.getString("city");
				String emailAddress = set.getString("email_address");
				boolean subscribed = set.getString("subscribed").toLowerCase().equals("true");
				
				customers.add(new Customer(i, firstName, lastName, address, zipCode, city, emailAddress, subscribed));
			}
			stmt.close();
		}catch (Exception e){			
			try {
				stmt.close();
			} catch (SQLException e1) {}
			
			throw new DbException("Couldn't get all customers.");
		}
		
		return customers;
	}

	public Product getLastAddedProduct() throws DbException {
		Product p = null;
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM " + productsTableName + " WHERE time_stamp IN (SELECT max(time_stamp) FROM "+productsTableName+")");
			set.next();
			int i = Integer.parseInt(set.getString("id"));
			String name = set.getString("title");
			String classStr = set.getString("class");
			String stateStr = set.getString("state");
			stmt.close();
			
			Class<?> clazz = Class.forName(classStr);
			Constructor<?> ctor = clazz.getConstructor(int.class,String.class,ProductStateEnum.class);
			p = (Product)ctor.newInstance((i),name,ProductStateEnum.valueOf(stateStr));	
			stmt.close();
		}catch(Exception e){
			try {
				stmt.close();
			} catch (SQLException e1) {}
			throw new DbException("Couldn't find the last added product");
		}
		return p;
	}


}
