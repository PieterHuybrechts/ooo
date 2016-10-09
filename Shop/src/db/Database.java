package db;

import java.util.List;

import domain.Customer;
import domain.Product;

public interface Database {

	public void addProduct(Product p) throws DbException;
	public void addCustomer(Customer c) throws DbException;
	public Product getProduct(int id) throws DbException;
	public Customer getCustomer(int id);
	public void deleteProduct(int id) throws DbException;
	public void updateProduct(Product p) throws DbException;
	public void updateCustomer(Customer c);
	public List<Customer> getAllCustomers();
	public List<Customer> getAllSubscribedCustomers();
	public Product getLastAddedProduct();
}
