package domain;

public class Rentable implements ProductState {

	public void rent(Product p) {
		p.setCurrentState(ProductStateEnum.RENTED);
	}

	public void returnToShop(Product p,boolean damaged) throws DomainException {
		throw new DomainException("The item can't be return while it is in the shop.");

	}

	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED);

	}

	public void repair(Product p) throws DomainException {
		throw new DomainException("The item can't be repaired while it is in the shop.");
		
	}

}
