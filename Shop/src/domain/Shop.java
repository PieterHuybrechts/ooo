package domain;

import db.Database;
import db.DbException;
import db.TxtDb;

public class Shop {
	
	Database db;
	
	
	public Shop() throws DbException{
		db=new TxtDb();
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
		db.modifyProduct(p);
	}
	
	public void returnProduct(int id,boolean damaged) throws DomainException, DbException{
		Product p = getProduct(id);
		p.returnToShop(damaged);
		db.modifyProduct(p);
	}
	
	public void deleteProduct(int id) throws DomainException, DbException{
		Product p = getProduct(id);
		p.delete();
		db.modifyProduct(p);
	}
	
	public void repairProduct(int id) throws DbException, DomainException{
		Product p = getProduct(id);
		p.repair();
		db.modifyProduct(p);
	}

}
