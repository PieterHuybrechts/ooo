package domain;

public class Deleted implements ProductState {

	@Override
	public void rent(Product p) throws DomainException {
		throw new DomainException("The item can't be rented when deleted.");

	}

	@Override
	public void returnToShop(Product p,boolean damaged) throws DomainException {
		throw new DomainException("The item can't be returned when deleted.");

	}

	@Override
	public void delete(Product p) throws DomainException {
		throw new DomainException("The item can't be deleted when deleted.");

	}

	@Override
	public void repair(Product p) throws DomainException {
		throw new DomainException("The item can't be repaired when deleted.");
		
	}

}
