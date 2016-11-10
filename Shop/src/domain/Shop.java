package domain;

import java.util.ArrayList;
import java.util.List;

import common.Observer;
import common.PropertiesEnum;
import common.Subject;
import db.Database;
import db.DatabaseFactory;
import db.DbException;
import domain.products.Product;
import domain.products.ProductFactory;
import domain.products.producstates.ProductStateEnum;

public class Shop implements Subject{
	
	private Database db;
	private List<Observer> observers;
	
	public Shop(Database db) throws DbException{
		observers=new ArrayList<Observer>();
		this.db = db;
	}
	
	public Shop() throws DbException{
		observers = new ArrayList<Observer>();
		this.db=DatabaseFactory.createDb(PropertiesEnum.DBTYPE.getProperty(), PropertiesEnum.DBURL.getProperty());
	}
	
	public void addProduct(Product p) throws DomainException{
		try{
			db.addProduct(p);
			notifyObservers();
		}catch(DbException e){
			throw new DomainException(e.getMessage());
		}
	}
	
	public void addProduct(String className,int id,String title,ProductStateEnum state) throws DomainException{
		addProduct(ProductFactory.createProduct(className, id, title, state));
	}
	
	public Product getProduct(int id) throws DomainException{
		try{
			return db.getProduct(id);			
		}catch(DbException e){
			throw new DomainException(e.getMessage());
		}
	}
	
	public void removeProductFromDb(int id) throws DomainException{
		try{			
			db.deleteProduct(id);
		}catch(DbException e){
			throw new DomainException(e.getMessage());
		}
	}
	
	public Product getLastAddedProduct() throws DomainException{
		try{
			return db.getLastAddedProduct();
		}catch(DbException e){
			throw new DomainException(e.getMessage());
		}
	}
	
	public void rentProduct(int id) throws DomainException{
		Product p = getProduct(id);
		p.rent();
		try{
			db.updateProduct(p);
		}catch(DbException e){
			throw new DomainException(e.getMessage());
		}
		
	}
	
	public void returnProduct(int id,boolean damaged) throws DomainException{
		Product p = getProduct(id);
		p.returnToShop(damaged);
		try {
			db.updateProduct(p);
		} catch (DbException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	public void deleteProduct(int id) throws DomainException{
		Product p = getProduct(id);
		p.delete();
		try {
			db.updateProduct(p);
		} catch (DbException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	public void repairProduct(int id) throws DomainException{
		Product p = getProduct(id);
		p.repair();
		try {
			db.updateProduct(p);
		} catch (DbException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	
	
	/*public void addCustomer(Customer c) throws DbException {
		db.addCustomer(c);
	}*/
	
	public void addCustomer(int id, String firstName, String lastName, String address, String zipCode, String city, String eMailAddress, boolean subscribed) throws DomainException{
		Customer c = CustomerFactory.create(id, firstName, lastName, address, zipCode, city, eMailAddress, subscribed);
		try {
			db.addCustomer(c);
		} catch (DbException e) {
			throw new DomainException("There already is a customer registered with this id.");
		}
	}
	
	public Customer getCustomer(int id) throws DomainException {
		try {
			return db.getCustomer(id);
		} catch (DbException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	public void removeCustomerFromDb(int id){
		try {
			db.deleteCustomer(id);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getAllSubscribedCustomers() throws DomainException{
		try {
			return db.getAllSubscribedCustomers();
		} catch (DbException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	public List<Customer> getAllCustomers() throws DomainException{
		try {
			return db.getAllCustomers();
		} catch (DbException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}

	
	
	public void deleteObserver(Observer o) {
		observers.remove(o);
		
	}

	public void notifyObservers() {
		for(Observer o : observers){
			o.update();
		}		
	}

	public List<Product> getAllProducts() throws DomainException {
		try {
			return db.getAllProducts();
		} catch (DbException e) {
			throw new DomainException(e.getMessage());
		}
	}



}
