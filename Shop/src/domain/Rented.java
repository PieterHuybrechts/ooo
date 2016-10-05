package domain;

import app.MagicStrings;

public class Rented implements ProductState {

	public void rent(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTRENTABLE.getError());
	}

	public void returnToShop(Product p,boolean damaged) {
		if(damaged){
			p.setCurrentState(ProductStateEnum.DAMAGED);
		}else{
			p.setCurrentState(ProductStateEnum.RENTABLE);
		}
	}

	public void delete(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTDELETABLE.getError());
	}

	public void repair(Product p) throws DomainException {
		throw new DomainException(MagicStrings.NOTREPAIRABLE.getError());	
	}

}
