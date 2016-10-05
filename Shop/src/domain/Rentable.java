package domain;

import app.MagicStrings;

public class Rentable implements ProductState {

	public void rent(Product p) {
		p.setCurrentState(ProductStateEnum.RENTED);
	}

	public void returnToShop(Product p,boolean damaged) throws DomainException {
		throw new DomainException(MagicStrings.NOTRETURNABLE.getError());
	}

	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED);
	}

	public void repair(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTREPAIRABLE.getError());	
	}

}
