package domain.products.producstates;

import domain.DomainException;
import domain.products.Product;

public class Damaged extends ProductState {

	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED);

	}

	public void repair(Product p) throws DomainException {
		p.setCurrentState(ProductStateEnum.RENTABLE);
		
	}

}
