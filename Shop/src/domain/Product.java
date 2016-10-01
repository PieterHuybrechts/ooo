package domain;

public abstract class Product {
	private int id;
	private String title;

	private ProductState currentState;

	public Product(){
		
	}
	
	public Product(String title, int id, ProductState pS) throws DomainException {
		this.setTitle(title);
		this.setId(id);
		this.setCurrentState(pS);
	}
	
	private void setId(int id) throws DomainException {
		if (id < 0) {
			throw new DomainException("geef een getal groter dan 0");
		}
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	private void setTitle(String title) throws DomainException {
		if (title.isEmpty()) {
			throw new DomainException("Typ een naam");
		}
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	protected void setCurrentState(ProductState state){
		this.currentState = state;
	}
	
	public ProductState getCurrentState(){
		return this.currentState;
	}

/*	public void returnToShop() throws DomainException{
		currentState.returnToShop(this);
	}
	
	public void rent() throws DomainException{
		currentState.rent(this);
	}
	
	public void delete() throws DomainException{
		currentState.delete(this);
	}
	
	public void damage() throws DomainException{
		currentState.damage(this);
	}
	
	public void remove() throws DomainException{
		currentState.delete(this);
	}*/

	public abstract int getPrice(int days);
}
