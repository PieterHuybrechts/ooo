package domain.products.producstates;

import domain.products.Product;

public class Rented extends ProductState {
	
	public void returnToShop(Product p,boolean damaged) {
		if(damaged){
			p.setCurrentState(ProductStateEnum.DAMAGED);
		}else{
			p.setCurrentState(ProductStateEnum.RENTABLE);
		}
	}
}
