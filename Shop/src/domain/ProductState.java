package domain;

public interface ProductState {
	public void rent(Product p) throws DomainException;
	public void returnToShop(Product p,boolean damaged) throws DomainException;
	public void delete(Product p) throws DomainException;
	public void damage(Product p) throws DomainException;
	public void repair(Product p) throws DomainException;
}