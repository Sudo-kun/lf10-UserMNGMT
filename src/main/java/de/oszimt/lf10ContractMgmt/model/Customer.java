package de.oszimt.lf10ContractMgmt.model;

import java.time.LocalDate;

public class Customer {
    private int customerID;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private String email;
    private Address address;
    private static int nextID = 1009900;  // this is only for internal usage.

    public Customer(String firstname, String lastname, LocalDate birthday, String email,
                    Address address) {
        super();
        this.customerID = generateNewID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
    }

    private int generateNewID() {
        return ++nextID;
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

    @Override
    public String toString() {
        return "Customer [customerID=" + customerID + ", firstname=" + firstname + ", lastname=" + lastname
                + ", birthday=" + birthday + ", email=" + email + ", address=" + address + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Customer) {
            Customer tempCustomer = (Customer) o;
            if (this.customerID == tempCustomer.getCustomerID())
                return true;
        }
        return false;
    }

}
