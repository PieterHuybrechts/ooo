package domain;

public class Rented extends ProductState {
	
	public void returnToShop(Product p,boolean damaged) {
		if(damaged){
			p.setCurrentState(ProductStateEnum.DAMAGED);
		}else{
			p.setCurrentState(ProductStateEnum.RENTABLE);
		}
	}
}
