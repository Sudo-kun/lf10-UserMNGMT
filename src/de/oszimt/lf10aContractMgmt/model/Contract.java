package de.oszimt.lf10aContractMgmt.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Contract {
	private int contractID;
	private LocalDate CreationDate;
	private Address ProjectLocations;
	private Customer customer;
	private Employee projectOwner;
	private String contractType;   
	private String state;          
	private String description;
	private ArrayList<ActivityRecord> activityRecordList;
	
	public Contract(int contractID, LocalDate creationDate, Address projectLocations, Customer customer,
			Employee projectOwner, String contractType, String state, String description,
			ArrayList<ActivityRecord> activityRecordList) {
		super();
		this.contractID = contractID;
		CreationDate = creationDate;
		ProjectLocations = projectLocations;
		this.customer = customer;
		this.projectOwner = projectOwner;
		this.contractType = contractType;
		this.state = state;
		this.description = description;
		this.activityRecordList = activityRecordList;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public LocalDate getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		CreationDate = creationDate;
	}

	public Address getProjectLocations() {
		return ProjectLocations;
	}

	public void setProjectLocations(Address projectLocations) {
		ProjectLocations = projectLocations;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getProjectOwner() {
		return projectOwner;
	}

	public void setProjectOwner(Employee projectOwner) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<ActivityRecord> getActivityRecordList() {
		return activityRecordList;
	}

	public void setActivityRecordList(ArrayList<ActivityRecord> activityRecordList) {
		this.activityRecordList = activityRecordList;
	}
	
}
