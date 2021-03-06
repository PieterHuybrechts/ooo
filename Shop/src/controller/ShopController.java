package controller;

import java.util.List;

import domain.Customer;
import domain.DomainException;
import domain.Shop;
import domain.products.Product;
import domain.products.ProductFactory;
import domain.products.producstates.ProductStateEnum;

public class ShopController {
	
	private Shop shop;
	
	public ShopController(Shop shop){
		setShop(shop);
	}
	
	private void setShop(Shop shop){
		this.shop = shop;
	}
	
	private Shop getShop(){
		return this.shop;
	}
	
	//Products
	
	public void addProduct(String className,int id,String title,ProductStateEnum state) throws DomainException{
		shop.addProduct(className, id, title, state);;
	}
	
	public void addProduct(String className,String id,String title,ProductStateEnum state) throws DomainException{
		int i;
		try{
			i = (Integer.parseInt(id));			
		}catch(NumberFormatException e){
			throw new DomainException("Id is not a number.");
		}
		
		this.addProduct(className, i, title, state);
	}
	
	public Product getProduct(int id) throws DomainException{
		return shop.getProduct(id);
	}
	
	public List<Product> getAllProducts() throws DomainException{
		return getShop().getAllProducts();
	}
	
	public void updateProduct(String className,String id,String title) throws DomainException{
		int i = Integer.parseInt(id);
		shop.updateProduct(ProductFactory.createProduct(className, i, title, getProduct(i).getCurrentState()));
	}
	
	//Customers
	
	public void addCustomer(int id, String firstName, String lastName, String address, String zipCode, String city, String eMailAddress, boolean subscribed) throws DomainException{
		shop.addCustomer(id, firstName, lastName, address, zipCode, city, eMailAddress, subscribed);
	}
	
	public void addCustomer(String id, String firstName, String lastName, String address, String zipCode, String city, String eMailAddress, boolean subscribed) throws DomainException{
		int i;
		try{
			i=Integer.parseInt(id);
		}catch (NumberFormatException e) {
			throw new DomainException("Id is not a number.");
		}
		
		shop.addCustomer(i, firstName, lastName, address, zipCode, city, eMailAddress, subscribed);
	}
	
	public Customer getCustomer(int id) throws DomainException{
		return shop.getCustomer(id);
	}
	
	public List<Customer> getAllCustomers() throws DomainException{
		return shop.getAllCustomers();
	}
	
	
	
}
