package de.oszimt.lf10ContractMgmt.impl;

import de.oszimt.lf10ContractMgmt.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HaseGmbHManagement implements IntContractMgmt, IntCustomerMgmt, IntEmployeeMgmt {

	private ArrayList<Employee> employeeList;
	private ArrayList<Customer> customerList;
	private ArrayList<Contract> contractList;

	public HaseGmbHManagement() {
		this.employeeList = new ArrayList<Employee>();
		this.customerList = new ArrayList<Customer>();
		this.contractList = new ArrayList<Contract>();

		setupTestEmployees();
		setupTestCustomers();
		setupTestContracts();
	}

	//
	// implementation of interface IntEmployeeMgmt
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
	// implementation of interface IntCustomerMgmt
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
	// implementation of interface IntContractMgmt
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

	
	// 
	//  methods for installing test data
	//

	public void setupTestEmployees() {

		Employee anEmployee = new Employee("Max", "Müller",
				new Address("Schloßweg", "5", "85777", "Berlin", "Deutschland"), "may.mueller@hase-gmbh.de",
				"0049301234545");
		addNewEmployee(anEmployee);

		anEmployee = new Employee("Lennart", "Jäger", new Address("Teckstraße", "19", "72141", "Berlin", "Deutschland"),
				"lennart.jaeger@hase-gmbh.de", "00493054234545");
		addNewEmployee(anEmployee);

		anEmployee = new Employee("Silvia", "Schäfers",
				new Address("Am Bergsteig", "2", "81541", "Berlin", "Deutschland"), "silvia.schaefers@hase-gmbh.de",
				"0049309856749");
		addNewEmployee(anEmployee);

		anEmployee = new Employee("Heiko", "Guempel",
				new Address("Petershausener Straße", "18", "85302", "Berlin", "Deutschland"),
				"heiko.guempel@hase-gmbh.de", "0049303456741");
		addNewEmployee(anEmployee);

		anEmployee = new Employee("Klaus", "Reder", new Address("Rißeckstraße", "30", "83727", "Berlin", "Deutschland"),
				"klaus.reder@hase-gmbh.de", "0049307686231");
		addNewEmployee(anEmployee);

	}

	public void setupTestCustomers() {

		Customer aCustomer;
		aCustomer = new Customer("Karin", "Nielsen", LocalDate.of(1970, 3, 21), "nielsen@acidlsdshop.com",
				new Address("Theodor-Storm-Straße", "23", "73529", "Berlin", "Deutschland"));
		addNewCustomer(aCustomer);

		aCustomer = new Customer("Timon", "Klier", LocalDate.of(1968, 8, 11), "klier68@gmail.com",
				new Address("Lübzer Straße", "6", "12529", "Berlin", "Deutschland"));
		addNewCustomer(aCustomer);

		aCustomer = new Customer("Egon", "Diederichsen", LocalDate.of(1975, 7, 4), "egondiederichsen@gmail.com",
				new Address("Loretostraße", "29", "10119", "Berlin", "Deutschland"));
		addNewCustomer(aCustomer);

		aCustomer = new Customer("Thore", "Rogowski", LocalDate.of(1963, 5, 14), "rogowski@ebarg.net",
				new Address("Sandäckerstraße", "25", "14537", "Berlin", "Deutschland"));
		addNewCustomer(aCustomer);

		aCustomer = new Customer("Jan-Hendrik", "Kohnen", LocalDate.of(1985, 9, 2), "kohnen@gmx.net",
				new Address("Pfarrer-Baumann-Straße", "9", "10577", "Berlin", "Deutschland"));
		addNewCustomer(aCustomer);

		aCustomer = new Customer("Nils", "Mantel", LocalDate.of(1955, 6, 19), "mantel@btcmail.org",
				new Address("Kattenescher Weg", "25", "13657", "Berlin", "Deutschland"));
		addNewCustomer(aCustomer);

		aCustomer = new Customer("Kai", "Wiese", LocalDate.of(1973, 4, 26), "wiese.kai@webmail.de",
				new Address("Dr.-Heffner-Straße", "33", "14112", "Berlin", "Deutschland"));
		addNewCustomer(aCustomer);

	}

	public void setupTestContracts() {
		// creating some ActivityRecords

		// for the contract 100001
		ActivityRecord ar01 = new ActivityRecord(LocalDate.of(2022, 6, 3), LocalTime.of(8, 30), LocalTime.of(13, 0),
				getAllEmployees().get(0), "Tätigkeit 01");
		ActivityRecord ar02 = new ActivityRecord(LocalDate.of(2022, 6, 11), LocalTime.of(10, 15), LocalTime.of(14, 30),
				getAllEmployees().get(0), "Tätigkeit 02");

		ActivityRecord ar03 = new ActivityRecord(LocalDate.of(2022, 6, 11), LocalTime.of(10, 15), LocalTime.of(14, 30),
				getAllEmployees().get(1), "Tätigkeit 02");
		ActivityRecord ar04 = new ActivityRecord(LocalDate.of(2022, 6, 12), LocalTime.of(9, 0), LocalTime.of(11, 30),
				getAllEmployees().get(1), "Tätigkeit 03");

		// for contract 100002
		ActivityRecord ar05 = new ActivityRecord(LocalDate.of(2022, 7, 17), LocalTime.of(14, 30), LocalTime.of(17, 0),
				getAllEmployees().get(2), "Tätigkeit 04");
		ActivityRecord ar06 = new ActivityRecord(LocalDate.of(2022, 7, 19), LocalTime.of(9, 30), LocalTime.of(15, 0),
				getAllEmployees().get(2), "Tätigkeit 07");

		// Employee 1004
		ActivityRecord ar07 = new ActivityRecord(LocalDate.of(2022, 7, 20), LocalTime.of(14, 30), LocalTime.of(17, 0),
				getAllEmployees().get(2), "Tätigkeit 04");

		ActivityRecord ar08 = new ActivityRecord(LocalDate.of(2022, 8, 2), LocalTime.of(9, 30), LocalTime.of(15, 0),
				getAllEmployees().get(2), "Tätigkeit 07");

		// creating some activity lists
		ArrayList<ActivityRecord> arList01 = new ArrayList<ActivityRecord>();
		arList01.add(ar01);
		arList01.add(ar02);
		arList01.add(ar03);
		arList01.add(ar04);

		ArrayList<ActivityRecord> arList02 = new ArrayList<ActivityRecord>();
		arList02.add(ar05);
		arList02.add(ar06);
		arList02.add(ar07);
		arList02.add(ar08);

		// initialisation of contracts

		Contract aContract;
		aContract = new Contract(LocalDate.of(2022, 4, 10),
				new Address("Theodor-Storm-Straße", "23", "73529", "Berlin", "Deutschland"), getAllCustomers().get(0),
				getAllEmployees().get(0), "Warung", "fertiggestsellt",
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit.", arList01);
		addNewContract(aContract);

		aContract = new Contract(LocalDate.of(2022, 2, 10),
				new Address("Theodor-Storm-Straße", "23", "73529", "Berlin", "Deutschland"), getAllCustomers().get(1),
				getAllEmployees().get(1), "Warung", "fertiggestsellt",
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit.", arList02);
		addNewContract(aContract);
	}

}
