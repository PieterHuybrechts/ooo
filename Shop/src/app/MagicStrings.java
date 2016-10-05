package app;

public enum MagicStrings {
	NOTIMPLEMENTED("Functionality not yet implemented"),
	EXISTINGIDINDB("There is already an product with with this id in the db. Id="),
	PRODUCTNOTFOUNDINDB("There is no product with this id in the db. Id="),
	DBCREATIONERROR("Something went wrong while creating the db"),
	INVALLIDID("Enter a number higher than 0"),
	INVALLIDNAME("Enter a name"),
	NOTRENTABLE("This item can't be rented."),
	NOTRETURNABLE("This item can't be returned."),
	NOTDELETABLE("This item can't be deleted."),
	NOTREPAIRABLE("This item can't be repaired.");
	
	
	
	private final String ERROR;
	
	MagicStrings(String error){
		this.ERROR=error;
	}
	
	public String getError(){
		return this.ERROR;
	}
}
