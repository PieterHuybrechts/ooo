package db;

import domain.Product;
import domain.ProductStateEnum;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.MagicStrings;
import db.DbException;

public class RelationalDb implements Database {

	
	private static String dbURL = "jdbc:derby:resources/shopDb;create=true";
	private static String tableName = "products";
	private static Connection conn = null;
	private static Statement stmt =null;
	
	public RelationalDb() throws DbException{
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
			stmt.execute("CREATE TABLE "+tableName+" (id INTEGER PRIMARY KEY, title VARCHAR(20), class VARCHAR(20), state VARCHAR(20))");
			stmt.close();
		}catch(SQLException e){
			
		}
		
	}	
	
	public void addProduct(Product p) throws DbException{
			try{
				stmt = conn.createStatement();
				stmt.execute("INSERT INTO "+ tableName + " VALUES (" + p.getId() + ",'" + p.getTitle() + "','" + p.getClass().getName() + "','" + p.getCurrentState() + "')");
				stmt.close();
			}catch(SQLException e){
				throw new DbException(MagicStrings.EXISTINGIDINDB.getError()+p.getId());
			}
			
	}

	public Product getProduct(int id) throws DbException{
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id="+id);
			
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
			throw new DbException(MagicStrings.PRODUCTNOTFOUNDINDB.getError()+id);
		}
	}

	public void deleteProduct(int id) throws DbException {
		try{
			stmt = conn.createStatement();
			stmt.execute("DELETE FROM "+tableName+" WHERE id="+id);
			stmt.close();
		}catch(SQLException e){
			throw new DbException(MagicStrings.NOTDELETABLE.getError());
		}		
	}

	public void updateProduct(Product p) throws DbException {
		try {
			stmt = conn.createStatement();
			stmt.execute("UPDATE " + tableName + 
				    " SET state='"+p.getCurrentState()+
				    "' WHERE id=" +p.getId() );
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DbException("Could not update this product");
		}
		
	}
	
	
	
}
