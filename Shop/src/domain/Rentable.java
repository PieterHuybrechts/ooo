package domain;

public class Rentable extends ProductState {

	public void rent(Product p) {
		p.setCurrentState(ProductStateEnum.RENTED);
	}

	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED);
	}

}
