package de.oszimt.lf10ContractMgmt.view;

import java.time.LocalDate;
import java.util.Date;

// create Class SearchCriteria taht contains the search criteria from the search window
public class SearchCriteria {
    private String customer;
    private String projectOwner;
    private String contractType;
    private String state;
    private Date startDate;
    private Date endDate;

    public SearchCriteria(String customer, String projectOwner, String contractType, String state, Date startDate, Date endDate) {
        this.customer = customer;
        this.projectOwner = projectOwner;
        this.contractType = contractType;
        this.state = state;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getStartDate() {
        return endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().atStartOfDay().toLocalDate();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().atStartOfDay().toLocalDate();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String toString() {
        return "Criteria [customer=" + customer + ", projectOwner=" + projectOwner + ", contractType="
                + contractType + ", state=" + state + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}
