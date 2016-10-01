package domain;

public enum ProductStateEnum {

	RENTABLE("rentable",new Rentable()),
	RENTED("rented",new Rented()),
	DAMAGED("damaged",new Damaged()),
	DELETED("deleted",new Deleted());
	
	private final String name;
	private final ProductState state;
	
	ProductStateEnum(String name,ProductState state){
		this.name=name;
		this.state = state;
	}
	
	public String getName(){
		return name;
	}
	
	public ProductState getState(){
		return state;
	}
	
	
	
}
