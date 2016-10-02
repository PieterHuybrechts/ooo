package domain;

import java.util.HashMap;
import java.util.Map;

public class Shop {
	
	private Map<Integer,Product> products;	
	
	public Shop(){
		this.products = new HashMap<Integer,Product>();
	}
	
	public void addProduct(Product p) throws DomainException{
		Integer id = new Integer(p.getId());
		
		if(this.products.containsKey(id)){
			throw new DomainException("Product id \""+id+"\" already exists.");
		}
		
		products.put(id, p);	
	}
	
	public Product getProduct(int id) throws DomainException{
		Product p = null;
		p = products.get(new Integer(id));
		
		if(p == null){
			throw new DomainException("Product with id \""+ id + "\" not found");
		}
		
		return p;
	}
	
	public void rentProduct(int productId) throws DomainException{
		try{
			getProduct(productId).rent();
		}catch(Exception e){
			throw e;
		}
	}
	
	public void returnProduct(int productId,boolean damaged) throws DomainException{
		try{
			getProduct(productId).returnToShop(damaged);
		}catch(Exception e){
			throw e;
		}
	}
	
	public void deleteProduct(int productId) throws DomainException{
		try{
			getProduct(productId).delete();
		}catch(Exception e){
			throw e;
		}
	}
	
	public void repairProduct(int productId) throws DomainException{
		try{
			getProduct(productId).repair();
		}catch(Exception e){
			throw e;
		}

	}
}
