package domain;

import app.MagicStrings;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String zipCode;
	private String city;
	private String emailAddress;
	private boolean subscribed;
	
	public Customer(int id,String firstName,String lastName,String address,String zipCode,String city,String emailAddress,boolean subscribed) throws DomainException{
		setId(id);
		setAchternaam(lastName);
		setVoornaam(firstName);
		setAddress(address);
		setZipCode(zipCode);
		setPlaats(city);
		setEmailAddress(emailAddress);
		setSubscribed(subscribed);
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) throws DomainException {
		if(id<0){
			throw new DomainException(MagicStrings.INVALLIDID.getError());
		}
		
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	private void setVoornaam(String firstName) throws DomainException {
		if(firstName == null || firstName.isEmpty()){
			throw new DomainException(MagicStrings.INVALLIDNAME.getError());
		}
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	private void setAchternaam(String lastName) throws DomainException {
		if(lastName == null || lastName.isEmpty()){
			throw new DomainException(MagicStrings.INVALLIDNAME.getError());
		}
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	private void setAddress(String address) throws DomainException {
		if(address == null || address.isEmpty()){
			throw new DomainException(MagicStrings.INVALLIDADDRESS.getError());
		}
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	private void setZipCode(String zipCode) throws DomainException {
		if(zipCode == null || zipCode.isEmpty() || Integer.parseInt(zipCode) < 0 || Integer.parseInt(zipCode) > 9999){
			throw new DomainException(MagicStrings.INVALLIDZIPCODE.getError());
		}
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	private void setPlaats(String city) throws DomainException {
		if(city == null || city.isEmpty()){
			throw new DomainException(MagicStrings.INVALLIDCITY.getError());
		}
		
		this.city = city;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	private void setEmailAddress(String emailAddress) throws DomainException {
		if(emailAddress == null || emailAddress.isEmpty()){
			throw new DomainException(MagicStrings.INVALLIDEMAILADDRESS.getError());
		}
		
		this.emailAddress = emailAddress;
	}
	public boolean isSubscribed() {
		return subscribed;
	}
	private void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
	
	public boolean equals(Object o){
		if(o instanceof Customer){
			Customer c = (Customer) o;
			if(getId()==c.getId() && getFirstName().equals(c.getFirstName()) && getLastName().equals(c.getLastName()) && getAddress().equals(c.getAddress()) && getZipCode().equals(c.getZipCode()) && getCity().equals(c.getCity()) && getEmailAddress().equals(c.getEmailAddress())){
				return true;
			}
		}
		
		return false;
	}
	
	
}
