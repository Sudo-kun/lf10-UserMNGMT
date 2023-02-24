package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.view.SearchCriteria;

public class EmployeeSearchCriteria {
  private String firstname;
  private String lastname;
  private String address;
  private String email;
  private String telefon;

  public EmployeeSearchCriteria(String firstname, String lastname, String address, String email, String telefon) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.telefon = telefon;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefon() {
    return telefon;
  }
}
