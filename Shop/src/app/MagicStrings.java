package app;

public enum MagicStrings {
	NOTIMPLEMENTED("Functionality not yet implemented"),
	
	//Db errors
	
	PRODUCTEXISTINGINDB("There is already a product with this id in the db. Id="),
	CUSTOMEREXISTINGINDB("There is already a customer with this id in the db. Id="),
	PRODUCTNOTFOUNDINDB("There is no product with this id in the db. Id="),
	CUSTOMERNOTFOUNDINDB("There is no customer with this id in the db. Id="),
	PRODUCTNOTDELETABLEFROMDB("The product with this id couldn't be deleted. Id="),
	CUSTOMERNOTDELETABLEFROMDB("The customer with this id couldn't be deleted. Id="),
	DBCREATIONERROR("Something went wrong while creating the db"),
	
	//Domain Errors
	
	INVALLIDID("Enter a number higher than 0"),
	INVALLIDNAMEENTERED("Enter a name"),
	INVALLIDNAME("A name can't be null or empty."),
	INVALLIDADDRESS("An address can't be null or empty"),
	INVALLIDCITY("A city name can't be null or empty."),
	INVALLIDZIPCODE("A zipcode has to be a number between 0 and"),
	INVALLIDEMAILADDRESS("An emailAddress can't be null or empty"),
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
