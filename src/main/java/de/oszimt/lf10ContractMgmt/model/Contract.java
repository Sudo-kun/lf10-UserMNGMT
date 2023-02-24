package de.oszimt.lf10ContractMgmt.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Contract {
	private int contractID;
	private LocalDate creationDate;
	private Address projectLocations;
	private Customer customer;
	private Employee projectOwner;
	private String contractType;   
	private String state;          
	private String description;
	private ArrayList<ActivityRecord> activityRecordList;
	
	private static int nextID = 21001100; // this is only for internal usage.
	
	public Contract(LocalDate creationDate, Address projectLocations, Customer customer,
			Employee projectOwner, String contractType, String state, String description,
			ArrayList<ActivityRecord> activityRecordList) {
		super();
		this.contractID = generateNewID();
		this.creationDate = creationDate;
		this.projectLocations = projectLocations;
		this.customer = customer;
		this.projectOwner = projectOwner;
		this.contractType = contractType;
		this.state = state;
		this.description = description;
		if (activityRecordList != null)
			this.activityRecordList = activityRecordList;
		else
			this.activityRecordList = new ArrayList<ActivityRecord>();
	}

	private int generateNewID() {
		return ++nextID;
	}
	
	public int getContractID() {
		return this.contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		creationDate = creationDate;
	}

	public Address getProjectLocations() {
		return projectLocations;
	}

	public void setProjectLocations(Address projectLocations) {
		projectLocations = projectLocations;
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

	@Override
	public String toString() {
		return "Contract [contractID=" + contractID + ", creationDate=" + creationDate + ", projectLocations="
				+ projectLocations + ", customer=" + customer.getLastname() + ", projectOwner=" + projectOwner.getLastname() + ", contractType="
				+ contractType + ", state=" + state + ", description=" + description + ", activityRecordList="
				+ activityRecordList + "]";
	}

	@Override
	public boolean equals(Object o) {
		if ( o instanceof Contract) {
			Contract tempContract = (Contract) o;
			if (this.contractID == tempContract.getContractID())
				return true;
		}
		return false;
	}
}
