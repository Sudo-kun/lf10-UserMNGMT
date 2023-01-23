package de.oszimt.lf10aContractMgmt.model;

public class Address {
	private String street;
	private String house;
	private String postalCode;
	private String city;
	private String country;
	
	public Address(String street, String house, 
			       String postalCode, String city, String country) {
		super();
		this.street = street;
		this.house = house;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	

}
