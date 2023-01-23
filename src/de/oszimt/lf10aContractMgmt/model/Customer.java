package de.oszimt.lf10aContractMgmt.model;

import java.time.LocalDate;

public class Customer {
	private int customerID;
	private String firstname;
	private String lastname;
	private LocalDate birthday;
	private String email;
	private Address address;
	
	public Customer(int customerID, String firstname, String lastname, LocalDate birthday, String email,
			Address address) {
		super();
		this.customerID = customerID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	
	

}
