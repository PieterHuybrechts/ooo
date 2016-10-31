package domain;

import common.MagicStrings;

public abstract class Product {
	private int id;
	private String title;
	private ProductStateEnum currentState;

	public Product(){
		
	}
	
	public Product(String title, int id, ProductStateEnum state) throws DomainException {
		this.setTitle(title);
		this.setId(id);
		this.setCurrentState(state);
	}
	
	private void setId(int id) throws DomainException {
		if (id < 0) {
			throw new DomainException(MagicStrings.INVALLIDID.getError());
		}
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	private void setTitle(String title) throws DomainException {
		if (title.isEmpty()) {
			throw new DomainException(MagicStrings.INVALLIDNAME.getError());
		}
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	protected void setCurrentState(ProductStateEnum state){
		this.currentState = state;
	}
	
	public ProductStateEnum getCurrentState(){
		return this.currentState;
	}

	public void returnToShop(boolean damaged) throws DomainException{
		getCurrentState().getState().returnToShop(this,damaged);
	}
	
	public void rent() throws DomainException{
		getCurrentState().getState().rent(this);
	}
	
	public void delete() throws DomainException{
		getCurrentState().getState().delete(this);
	}
	
	public void repair() throws DomainException{
		getCurrentState().getState().repair(this);
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Product){
			Product p = (Product) o;
			if(this.getId()!=p.getId()){
				return false;
			}else if(!this.getTitle().equals(p.getTitle())){
				return false;
			}
			
			return true;
		}
		
		return false;
	}

	public abstract int getPrice(int days);
}
