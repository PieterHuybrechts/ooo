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
	
	public Product getProduct(int id){
		Product p = null;
		p = products.get(new Integer(id));
		
		return p;
	}
	
}
