package de.oszimt.lf10ContractMgmt.model;

public class Employee {
    private int employeeID;
    private String firstname;
    private String lastname;
    private Address address;
    private String email;
    private String telephone;
    private static int nextID = 10000;   // this is only for internal usage.

    public Employee(String firstname, String lastname,
                    Address address, String email, String telephone) {
        this.employeeID = generateNewID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
    }

    private int generateNewID() {
        return ++nextID;
    }


    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Employee [employeeID=" + employeeID + ", firstname=" + firstname + ", lastname=" + lastname
                + ", address=" + address + ", email=" + email + ", telephone=" + telephone + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Employee) {
            Employee tempEmployee = (Employee) o;
            if (this.employeeID == tempEmployee.getEmployeeID())
                return true;
        }
        return false;
    }


}
