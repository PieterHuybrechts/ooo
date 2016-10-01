package domain;

public class Rentable implements ProductState {

	@Override
	public void rent(Product p) {
		p.setCurrentState(ProductStateEnum.RENTED.getState());
	}

	@Override
	public void returnToShop(Product p,boolean damaged) throws DomainException {
		throw new DomainException("The item can't be return while it is in the shop.");

	}

	@Override
	public void delete(Product p) {
		p.setCurrentState(ProductStateEnum.DELETED.getState());

	}

	@Override
	public void damage(Product p) throws DomainException {
		throw new DomainException("The item can't be deleted while it is in the shop.");

	}

	@Override
	public void repair(Product p) throws DomainException {
		throw new DomainException("The item can't be repaired while it is in the shop.");
		
	}

}
