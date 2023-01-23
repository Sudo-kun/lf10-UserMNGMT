package de.oszimt.lf10aContractMgmt.model;
import java.util.ArrayList;

public interface IntEmployeeMagmt {
		
	/**
	 * add a new employee 
	 * @param newEmployee
	 */
	void addNewEmployee(Employee newEmployee);
  
	/**
	 * Returns the employee whose ID was passed as a parameter
	 * @param employeeID
	 * @return an object of class Employee
	 */
	Customer getEmployee(int employeeID);
	
	/**
	 * Returns a list with all employees
	 * @return {@link ArrayList}
	 */
	ArrayList<Employee> getAllEmployees();
	
	/**
	 * Replaces an existing employee with the employee 
	 * which was transferred as a parameter
	 * @param anEmployee
	 * @return true, if it was successful, false otherwise
	 */
	boolean updateEmployee(Employee anEmployee);
	
	/**
	 * Removes an existing employee
	 * @param an employeeID
	 * @return true if it was successful, false otherwise
	 */
	boolean deleteEmployee(int customerID);

}
