package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.util.DateUtils;

import java.time.LocalDate;
import java.util.Date;

public class CustomerSearchCriteria {
  private String firstname;
  private String lastname;
  private String address;
  private String email;
  private Date startDate;
  private Date endDate;

  public CustomerSearchCriteria(String firstname, String lastname, String address, String email, Date startDate, Date endDate) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public LocalDate getEndDate() {
    return DateUtils.asLocalDate(endDate);
  }

  public LocalDate getStartDate() {
    return DateUtils.asLocalDate(startDate);
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getLastname() {
    return lastname;
  }

  public String getFirstname() {
    return firstname;
  }
}
