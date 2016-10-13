package db;

import java.util.List;

import domain.Customer;
import domain.Product;

public interface Database {

	public void addProduct(Product p) throws DbException;
	public Product getProduct(int id) throws DbException;
	public void deleteProduct(int id) throws DbException;
	public void updateProduct(Product p) throws DbException;
	
	public void addCustomer(Customer c) throws DbException;
	public Customer getCustomer(int id) throws DbException;
	public void deleteCustomer(int id) throws DbException;
	
	public List<Customer> getAllSubscribedCustomers() throws DbException;
	public Product getLastAddedProduct() throws DbException;
	public List<Customer> getAllCustomers() throws DbException;
}


