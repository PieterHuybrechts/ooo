package domain;

import common.MagicStrings;

public class ProductState{

	public void rent(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTRENTABLE.getError());
	}

	public void returnToShop(Product p, boolean damaged) throws DomainException {
		throw new DomainException(MagicStrings.NOTRETURNABLE.getError());
	}

	public void delete(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTDELETABLE.getError());
	}

	public void repair(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTREPAIRABLE.getError());
	}
	
}
