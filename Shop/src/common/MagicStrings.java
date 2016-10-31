package common;

public enum MagicStrings {
	NOTIMPLEMENTED("Functionality not yet implemented"),
	
	//Db errors
	
	PRODUCTEXISTINGINDB("A product with this id has already been added. Id="),
	CUSTOMEREXISTINGINDB("A customer with this id has already been added. Id="),
	PRODUCTNOTFOUNDINDB("Couldn't find a product with this id. Id="),
	CUSTOMERNOTFOUNDINDB("Couldn't find a customer with this id. Id="),
	PRODUCTNOTDELETABLEFROMDB("The product with this id couldn't be deleted. Id="),
	CUSTOMERNOTDELETABLEFROMDB("The customer with this id couldn't be deleted. Id="),
	DBCREATIONERROR("Something went wrong while creating the db"),
	
	//Domain Errors
	
	INVALLIDID("An id should be a number higher than 0."),
	INVALLIDNAME("A name can't be null or empty."),
	INVALLIDADDRESS("An address can't be null or empty"),
	INVALLIDCITY("A city name can't be null or empty."),
	INVALLIDZIPCODE("A zipcode has to be a number between 0000 and 9999"),
	INVALLIDEMAILADDRESS("An emailAddress can't be null or empty"),
	NOTRENTABLE("This item can't be rented."),
	NOTRETURNABLE("This item can't be returned."),
	NOTDELETABLE("This item can't be deleted."),
	NOTREPAIRABLE("This item can't be repaired."),
	FACTORYERROR("Couldn't create this product.");
	
	private final String ERROR;
	
	MagicStrings(String error){
		this.ERROR=error;
	}
	
	public String getError(){
		return this.ERROR;
	}
}
