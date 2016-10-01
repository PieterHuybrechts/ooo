package db;

import domain.Product;

public interface Database {

	public void addProduct(Product p);
	public void getProduct(int id);
	
}
