package domain;

public class Damaged implements ProductState {

	public void rent(Product p) throws DomainException {
		throw new DomainException("The item can't be rented when damaged.");

	}

	public void returnToShop(Product p,boolean damaged) throws DomainException {
		throw new DomainException("The item can't be rented when damaged.");

	}

	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED);

	}

	public void repair(Product p) throws DomainException {
		p.setCurrentState(ProductStateEnum.RENTABLE);
		
	}

}
