package domain;

public class CustomerFactory {

	public static Customer create(int id, String firstName, String lastName, String address, String zipCode, String city, String emailAddress,boolean subscribed) throws DomainException{
		return new Customer(id, firstName, lastName, address, zipCode, city, emailAddress, subscribed);
	}
	
}
