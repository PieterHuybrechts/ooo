package domain;

import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.DbException;

public class Shop implements Subject{
	
	private Database db;
	private List<Observer> observers;
	
	public Shop(Database db) throws DbException{
		observers=new ArrayList<Observer>();
		this.db = db;
	}
	
	public void addProduct(Product p) throws DbException{
		db.addProduct(p);
		notifyObservers();
	}
	
	public Product getProduct(int id) throws DbException{
		return db.getProduct(id);
	}
	
	public void removeProductFromDb(int id) throws DbException{
		db.deleteProduct(id);
	}
	
	public Product getLastAddedProduct() throws DbException{
		return db.getLastAddedProduct();
	}
	
	public void rentProduct(int id) throws DomainException, DbException{
		Product p = getProduct(id);
		p.rent();
		db.updateProduct(p);
	}
	
	public void returnProduct(int id,boolean damaged) throws DomainException, DbException{
		Product p = getProduct(id);
		p.returnToShop(damaged);
		db.updateProduct(p);
	}
	
	public void deleteProduct(int id) throws DomainException, DbException{
		Product p = getProduct(id);
		p.delete();
		db.updateProduct(p);
	}
	
	public void repairProduct(int id) throws DbException, DomainException{
		Product p = getProduct(id);
		p.repair();
		db.updateProduct(p);
	}
	
	
	
	public void addCustomer(Customer c) throws DbException {
		db.addCustomer(c);
	}
	
	public Customer getCustomer(int id) throws DbException{
		return db.getCustomer(id);
	}
	
	public void removeCustomerFromDb(int id) throws DbException{
		db.deleteCustomer(id);
	}

	public List<Customer> getAllSubscribedCustomers() throws DbException{
		return db.getAllSubscribedCustomers();
	}
	
	public List<Customer> getAllCustomers() throws DbException{
		return db.getAllCustomers();
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

}
