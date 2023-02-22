package de.oszimt.lf10ContractMgmt.model;
import java.util.ArrayList;

public interface IntCustomerMgmt {
	
	/**
	 * Add a new customer 
	 * @param newCustomer
	 */
	boolean addNewCustomer(Customer newCustomer);
  
	/**
	 * Returns the customer whose ID was passed as a parameter
	 * @param customerID
	 * @return Customer object
	 */
	Customer getCustomer(int customerID);
	
	/**
	 * Returns a list with all customers
	 * @return {@link ArrayList}
	 */
	ArrayList<Customer> getAllCustomers();
	
	/**
	 * Replaces an existing customer with the customer 
	 * which was transfered as a parameter
	 * @param aCustomer
	 * @return true if it was successful, else false
	 */
	boolean updateCustomer(Customer aCustomer);
	
	/**
	 * Removes an existing customer
	 * @param a customerID
	 * @return true if it was successful, false otherwise
	 */
	boolean deleteCustomer(int customerID);

}
