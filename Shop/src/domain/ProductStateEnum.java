package domain;

public enum ProductStateEnum {

	RENTABLE("rentable",new Rentable()),
	RENTED("rented",new Rented()),
	DAMAGED("damaged",new Damaged()),
	DELETED("deleted",new Deleted());
	
	private final String NAME;
	private final ProductState STATE;
	
	ProductStateEnum(String name,ProductState state){
		this.NAME=name;
		this.STATE = state;
	}
	
	public String getName(){
		return NAME;
	}
	
	public ProductState getState(){
		return STATE;
	}
	
	/*public String toString(){
		return getName();
	}*/
	
	
	
}
