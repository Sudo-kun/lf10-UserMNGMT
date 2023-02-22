package de.oszimt.lf10ContractMgmt.model;
import java.util.ArrayList;

public interface IntContractMgmt {
	
	/**
	 * add a new contract 
	 * @param newContract
	 */
	boolean addNewContract(Contract newContract);
  
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
	ArrayList<Contract> getAllContracts();
	
	/**
	 * Replaces an existing contract with the contract 
	 * which was transferred as a parameter
	 * @param aContract
	 * @return true if it was successful, else false
	 */
	boolean updateCustomer(Contract aContract);
	
	
	boolean addNewWorkingRecord(int contractID, ActivityRecord  aRecord);
	
	
	/**
	 * Removes an existing contract
	 * @param aContractID
	 * @return true if it was successful, false otherwise
	 */
	boolean deleteContract(int contractID);

}
