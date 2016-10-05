package db;

import domain.Product;
import db.DbException;

public class RelationalDb implements Database {

	public RelationalDb() {
		
	}
	
	public void addProduct(Product p) throws DbException {
		throw new DbException("not implemented");

	}

	public Product getProduct(int id) throws DbException{
		throw new DbException("not implemented");
		
		
	}

	public void deleteProduct(int id) throws DbException {
		throw new DbException("not implemented");
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public boolean containsId(int id) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
