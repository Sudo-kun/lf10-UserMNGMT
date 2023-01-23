package de.oszimt.lf10aContractMgmt.model;
import java.util.ArrayList;

public interface IntContractMagmt {
	
	/**
	 * add a new contract 
	 * @param newContract
	 */
	void addNewContract(Contract newContract);
  
	/**
	 * Returns the contract whose ID was passed as a parameter
	 * @param contractID
	 * @return a contract object
	 */
	Contract getContract(int contractID);
	
	/**
	 * Returns a list with all contracts
	 * @return {@link ArrayList}
	 */
	ArrayList<Customer> getAllContracts();
	
	/**
	 * Replaces an existing contract with the contract 
	 * which was transferred as a parameter
	 * @param aContract
	 * @return true if it was successful, else false
	 */
	boolean updateCustomer(Contract aContract);
	
	
	void addNewWorkingRecord(int contractID, ActivityRecord  aWorkingRecord);
	
	
	/**
	 * Removes an existing contract
	 * @param aContractID
	 * @return true if it was successful, false otherwise
	 */
	boolean deleteContract(int contractID);

}
