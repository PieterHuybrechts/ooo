package domain;

public class Rented implements ProductState {

	@Override
	public void rent(Product p) throws DomainException {
		throw new DomainException("The item can't be rented while it is rented.");

	}

	@Override
	public void returnToShop(Product p,boolean damaged) {
		if(damaged){
			p.setCurrentState(ProductStateEnum.DAMAGED.getState());
		}else{
			p.setCurrentState(ProductStateEnum.RENTABLE.getState());
		}

	}

	@Override
	public void delete(Product p) throws DomainException {
		throw new DomainException("The item can't be deleted while it is rented.");

	}

	@Override
	public void damage(Product p) throws DomainException {
		throw new DomainException("The item can't be damaged while it is rented.");

	}

	@Override
	public void repair(Product p) throws DomainException {
		throw new DomainException("The item can't be repaired while it is rented.");
		
	}

}
