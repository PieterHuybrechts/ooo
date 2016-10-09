package domain;

import db.Database;
import db.DbException;
import db.SqlDb;

public class Shop implements Subject{
	
	private Database db;
	
	
	public Shop() throws DbException{
		db=new SqlDb();
	}
	
	public void addProduct(Product p) throws DbException{
		db.addProduct(p);
	}
	
	public Product getProduct(int id) throws DbException{
		return db.getProduct(id);
	}
	
	public void removeFromDb(int id) throws DbException{
		db.deleteProduct(id);
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

	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	public void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

}
