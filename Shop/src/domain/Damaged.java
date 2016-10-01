package domain;

public class Damaged implements ProductState {

	@Override
	public void rent(Product p) throws DomainException {
		throw new DomainException("The item can't be rented when damaged.");

	}

	@Override
	public void returnToShop(Product p,boolean damaged) throws DomainException {
		throw new DomainException("The item can't be rented when damaged.");

	}

	@Override
	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED.getState());

	}

	@Override
	public void damage(Product p) throws DomainException {
		throw new DomainException("The item can't be rented when damaged.");

	}

	@Override
	public void repair(Product p) throws DomainException {
		p.setCurrentState(ProductStateEnum.RENTABLE.getState());
		
	}

}
