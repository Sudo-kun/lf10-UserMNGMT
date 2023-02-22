package de.oszimt.lf10ContractMgmt.model;

import java.time.*;

public class ActivityRecord {
	private LocalDate  date;
	private LocalTime  startTime;
	private LocalTime  endTime;
	private Employee employee;
	private String description;
	
	public ActivityRecord(LocalDate date, LocalTime startTime, 
			LocalTime endTime, Employee employee, String description) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ActivityRecord [date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", employee="
				+ employee + ", description=" + description + "]";
	}
	
	
		
}
