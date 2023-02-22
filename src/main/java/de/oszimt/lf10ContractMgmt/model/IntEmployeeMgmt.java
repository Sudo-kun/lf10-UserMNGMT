package de.oszimt.lf10ContractMgmt.model;
import java.util.ArrayList;

public interface IntEmployeeMgmt {
		
	/**
	 * add a new employee 
	 * @param newEmployee
	 */
	boolean addNewEmployee(Employee newEmployee);
  
	/**
	 * Returns the employee whose ID was passed as a parameter
	 * @param employeeID
	 * @return an object of class Employee
	 */
	Employee getEmployee(int employeeID);
	
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
	boolean deleteEmployee(int employeeID);

}
