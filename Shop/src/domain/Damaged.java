package domain;

import app.MagicStrings;

public class Damaged implements ProductState {

	public void rent(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTRENTABLE.getError());
	}

	public void returnToShop(Product p,boolean damaged) throws DomainException {
		throw new DomainException(MagicStrings.NOTRETURNABLE.getError());
	}

	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED);

	}

	public void repair(Product p) throws DomainException {
		p.setCurrentState(ProductStateEnum.RENTABLE);
		
	}

}
