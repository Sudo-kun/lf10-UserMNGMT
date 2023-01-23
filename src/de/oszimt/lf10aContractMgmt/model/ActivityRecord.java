package de.oszimt.lf10aContractMgmt.model;

import java.time.LocalDateTime;

public class ActivityRecord {
	private LocalDateTime  startTime;
	private LocalDateTime  endTime;
	private int numberOfEmployees;
	private String description;
	
	public ActivityRecord(LocalDateTime startTime, LocalDateTime endTime, int numberOfEmployees, String description) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.numberOfEmployees = numberOfEmployees;
		this.description = description;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		
}
