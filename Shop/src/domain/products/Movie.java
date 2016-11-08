package domain.products;

import domain.DomainException;
import domain.products.producstates.ProductStateEnum;

public class Movie extends Product{
	
	
			
	public  int getPrice(int days){
		int prijs = 5;
		int daysLeft = days - 3;
		if (daysLeft > 0){
			prijs += (daysLeft * 2);
		}
		return prijs;
	}	
	
	public Movie (){
		super();
	}
	
	public Movie (int id, String title, ProductStateEnum state) throws DomainException{
		super (title,id,state);
	}
}
