package de.oszimt.lf10ContractMgmt.impl;

import de.oszimt.lf10ContractMgmt.model.*;

import java.util.ArrayList;

public class HaseGmbHManagement implements IntContractMgmt, IntCustomerMgmt, IntEmployeeMgmt {

    private final ArrayList<Employee> employeeList;
    private final ArrayList<Customer> customerList;
    private final ArrayList<Contract> contractList;

    public HaseGmbHManagement() {
        this.employeeList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.contractList = new ArrayList<>();
    }

    //
    //  implementation of interface IntEmployeeMgmt
    //

    @Override
    public boolean addNewEmployee(Employee newEmployee) {
        for (Employee e : employeeList)
            if (e.equals(newEmployee)) {
                System.err.println("Error: employee id in used.");
                return false;
            }
        employeeList.add(newEmployee);
        return true;
    }

    @Override
    public Employee getEmployee(int employeeID) {
        for (Employee e : employeeList)
            if (e.getEmployeeID() == employeeID) {
                return e;
            }
        System.err.println("Error: employee does not exist.");
        return null;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return this.employeeList;
    }

    @Override
    public boolean updateEmployee(Employee anEmployee) {
        for (int i = 0; i < employeeList.size(); i++)
            if (employeeList.get(i).equals(anEmployee)) {
                employeeList.set(i, anEmployee);
                return true;
            }
        System.err.println("Error: the employee does not exist.");
        return false;
    }

    @Override
    public boolean deleteEmployee(int employeeID) {
        for (int i = 0; i < employeeList.size(); i++)
            if (employeeList.get(i).getEmployeeID() == employeeID) {
                employeeList.remove(i);
                return true;
            }
        System.err.println("Error: the employee does not exist.");
        return false;
    }

    //
    //  implementation of interface IntCustomerMgmt
    //
    //

    @Override
    public boolean addNewCustomer(Customer newCustomer) {
        for (Customer c : customerList)
            if (c.equals(newCustomer)) {
                System.err.println("Error: customer id in used.");
                return false;
            }
        customerList.add(newCustomer);
        return true;

    }

    @Override
    public Customer getCustomer(int customerID) {
        for (Customer c : customerList)
            if (c.getCustomerID() == customerID) {
                return c;
            }
        System.err.println("Error: customer does not exist.");
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return customerList;
    }

    @Override
    public boolean updateCustomer(Customer aCustomer) {
        for (int i = 0; i < customerList.size(); i++)
            if (customerList.get(i).equals(aCustomer)) {
                customerList.set(i, aCustomer);
                return true;
            }
        System.err.println("Error: the customer does not exist.");
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerID) {
        for (int i = 0; i < employeeList.size(); i++)
            if (customerList.get(i).getCustomerID() == customerID) {
                customerList.remove(i);
                return true;
            }
        System.err.println("Error: the customer does not exist.");
        return false;
    }

    //
    //  implementation of interface IntContractMgmt
    //
    //

    @Override
    public boolean addNewContract(Contract newContract) {
        for (Contract c : contractList)
            if (c.equals(newContract)) {
                System.err.println("Error: contract id in used.");
                return false;
            }
        contractList.add(newContract);
        return true;
    }

    @Override
    public Contract getContract(int contractID) {
        for (Contract c : contractList)
            if (c.getContractID() == contractID) {
                return c;
            }
        System.err.println("Error: contract does not exist.");
        return null;
    }

    @Override
    public ArrayList<Contract> getAllContracts() {
        return contractList;
    }

    @Override
    public boolean updateCustomer(Contract aContract) {
        for (int i = 0; i < contractList.size(); i++)
            if (contractList.get(i).equals(aContract)) {
                contractList.set(i, aContract);
                return true;
            }
        System.err.println("Error: the contract does not exist.");
        return false;
    }

    @Override
    public boolean addNewWorkingRecord(int contractID, ActivityRecord aRecord) {
        Contract aContract = getContract(contractID);
        if (aContract != null)
            if (aContract.getActivityRecordList() != null) {
                aContract.getActivityRecordList().add(aRecord);
                return true;
            }
        return false;
    }

    @Override
    public boolean deleteContract(int contractID) {
        for (int i = 0; i < contractList.size(); i++)
            if (contractList.get(i).getContractID() == contractID) {
                contractList.remove(i);
                return true;
            }
        System.err.println("Error: the contract does not exist.");
        return false;
    }

}
