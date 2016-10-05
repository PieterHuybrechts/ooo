package db;

import domain.Product;

public interface Database {

	public void addProduct(Product p) throws DbException;
	public Product getProduct(int id) throws DbException;
	public void deleteProduct(int id) throws DbException;
	public void modifyProduct(Product p) throws DbException;
}
