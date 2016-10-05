package db;

import domain.DomainException;
import domain.Product;
import domain.ProductStateEnum;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.MagicStrings;
import db.DbException;

public class RelationalDb implements Database {
	
	private static String dbURL = "jdbc:derby:resources/database;create=true";
	private static String tableName = "products";
	private static Connection conn;
	private static Statement stmt;
	
	public RelationalDb() throws DbException {
		conn = createConnection();
		try{createTable();}catch(Exception e){}
	}
	
	private void createTable() throws DbException{
		try{
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE persons (id INTEGER PRIMARY KEY, title VARCHAR(20), class VARCHAR(20) , state VARCHAR(20))");
			stmt.close();
		}catch(SQLException e){
			throw new DbException("The table already exists");
		}
		
	}
	
	protected Connection createConnection() throws DbException{
		try{
			return DriverManager.getConnection(dbURL);	
		}catch(Exception e){
			throw new DbException("error during creating the connection");
		}
		
	}
	
	
	public void addProduct(Product p) throws DbException {
		try{
			stmt = conn.createStatement();
			stmt.execute("INSERT INTO "+ tableName + " VALUES (" + p.getId() + ",'" + p.getTitle() + "','" + p.getClass() + "','" + p.getCurrentState() + "')");
			stmt.close();
		}catch(SQLException e){
			throw new DbException(MagicStrings.EXISTINGIDINDB.getError()+p.getId());
		}
	}

	public Product getProduct(int id) throws DbException{
		try{
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM " + tableName + "AS t WHERE t.id="+id);
			stmt.close();
			
			set.next();
			int i = Integer.parseInt(set.getString("id"));
			String name = set.getString("name");
			String classStr = set.getString("class");
			String stateStr = set.getString("state");
			
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

	@Override
	public void modifyProduct(Product p) throws DbException {
		throw new DbException("not implemented");		
	}
	


}
