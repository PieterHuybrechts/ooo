package db;

import domain.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.DbException;

public class RelationalDb implements Database {
	
	public RelationalDb() throws DbException {
		throw new DbException("not implemented");
	}
	
	public void addProduct(Product p) throws DbException {
		throw new DbException("not implemented");

	}

	public Product getProduct(int id) throws DbException{
		throw new DbException("not implemented");
	}

	public void deleteProduct(int id) throws DbException {
		throw new DbException("not implemented");
		
	}

	@Override
	public void modifyProduct(Product p) throws DbException {
		throw new DbException("not implemented");		
	}
	
	protected Connection createConnection() throws SQLException{
		String dbURL = "jdbc:derby:resources/database;create=true";
		Connection conn = DriverManager.getConnection(dbURL);
		return conn;
	}

}
