package app;

public enum MagicStrings {
	NOTIMPLEMENTD("Functionality not yet tested"),
	EXISTINGIDINDB("There is already an product with with this id in the db. Id="),
	PRODUCTNOTFOUNDINDB("There is no product with this id in the db. Id=");
	
	private final String ERROR;
	
	MagicStrings(String error){
		this.ERROR=error;
	}
	
	public String getError(){
		return this.ERROR;
	}
	
	public String toString(){
		return this.ERROR;
	}
}
