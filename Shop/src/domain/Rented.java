package domain;

public class Rented implements ProductState {

	@Override
	public void rent(Product p) throws DomainException {
		throw new DomainException("The item can't be rented while it is rented.");

	}

	@Override
	public void returnToShop(Product p,boolean damaged) {
		if(damaged){
			p.setCurrentState(ProductStateEnum.DAMAGED);
		}else{
			p.setCurrentState(ProductStateEnum.RENTABLE);
		}

	}

	@Override
	public void delete(Product p) throws DomainException {
		throw new DomainException("The item can't be deleted while it is rented.");

	}

	@Override
	public void repair(Product p) throws DomainException {
		throw new DomainException("The item can't be repaired while it is rented.");
		
	}

}
