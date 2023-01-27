package de.oszimt.lf10ContractMgmt;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HaseGmbHClientSimulation {

    private HaseGmbHManagement haseMgmtDriver;

    public static void main(String[] args) {
        new HaseGmbHClientSimulation();
    }

    public HaseGmbHClientSimulation() {
        haseMgmtDriver = new HaseGmbHManagement();
        setupTestEmployees();
        setupTestCustomers();
        setupTestContracts();

        System.out.println(" list of the test-employees:");
        for (Employee e : haseMgmtDriver.getAllEmployees()) {
            System.out.println(e);
        }
        System.out.println("-------------------------------\n");
        System.out.println(" list of the test-customers:");
        for (Customer c : haseMgmtDriver.getAllCustomers()) {
            System.out.println(c);
        }
        System.out.println("-------------------------------\n");

        System.out.println(" list of the test-contracs:");
        for (Contract c : haseMgmtDriver.getAllContracts()) {
            System.out.println(c);
        }
    }


    public void setupTestEmployees() {

        Employee anEmployee = new Employee("Max", "Müller",
                new Address("Schloßweg", "5", "85777", "Berlin", "Deutschland"),
                "may.mueller@hase-gmbh.de", "0049301234545");
        haseMgmtDriver.addNewEmployee(anEmployee);

        anEmployee = new Employee("Lennart", "Jäger",
                new Address("Teckstraße", "19", "72141", "Berlin", "Deutschland"),
                "lennart.jaeger@hase-gmbh.de", "00493054234545");
        haseMgmtDriver.addNewEmployee(anEmployee);

        anEmployee = new Employee("Silvia", "Schäfers",
                new Address("Am Bergsteig", "2", "81541", "Berlin", "Deutschland"),
                "silvia.schaefers@hase-gmbh.de", "0049309856749");
        haseMgmtDriver.addNewEmployee(anEmployee);

        anEmployee = new Employee("Heiko", "Guempel",
                new Address("Petershausener Straße", "18", "85302", "Berlin", "Deutschland"),
                "heiko.guempel@hase-gmbh.de", "0049303456741");
        haseMgmtDriver.addNewEmployee(anEmployee);

        anEmployee = new Employee("Klaus", "Reder",
                new Address("Rißeckstraße", "30", "83727", "Berlin", "Deutschland"),
                "klaus.reder@hase-gmbh.de", "0049307686231");
        haseMgmtDriver.addNewEmployee(anEmployee);

    }

    public void setupTestCustomers() {

        Customer aCustomer;
        aCustomer = new Customer("Karin", "Nielsen", LocalDate.of(1970, 3, 21), "nielsen@acidlsdshop.com",
                new Address("Theodor-Storm-Straße", "23", "73529", "Berlin", "Deutschland"));
        haseMgmtDriver.addNewCustomer(aCustomer);

        aCustomer = new Customer("Timon", "Klier", LocalDate.of(1968, 8, 11), "klier68@gmail.com",
                new Address("Lübzer Straße", "6", "12529", "Berlin", "Deutschland"));
        haseMgmtDriver.addNewCustomer(aCustomer);

        aCustomer = new Customer("Egon", "Diederichsen", LocalDate.of(1975, 7, 4), "egondiederichsen@gmail.com",
                new Address("Loretostraße", "29", "10119", "Berlin", "Deutschland"));
        haseMgmtDriver.addNewCustomer(aCustomer);

        aCustomer = new Customer("Thore", "Rogowski", LocalDate.of(1963, 5, 14), "rogowski@ebarg.net",
                new Address("Sandäckerstraße", "25", "14537", "Berlin", "Deutschland"));
        haseMgmtDriver.addNewCustomer(aCustomer);

        aCustomer = new Customer("Jan-Hendrik", "Kohnen", LocalDate.of(1985, 9, 2), "kohnen@gmx.net",
                new Address("Pfarrer-Baumann-Straße", "9", "10577", "Berlin", "Deutschland"));
        haseMgmtDriver.addNewCustomer(aCustomer);

        aCustomer = new Customer("Nils", "Mantel", LocalDate.of(1955, 6, 19), "mantel@btcmail.org",
                new Address("Kattenescher Weg", "25", "13657", "Berlin", "Deutschland"));
        haseMgmtDriver.addNewCustomer(aCustomer);

        aCustomer = new Customer("Kai", "Wiese", LocalDate.of(1973, 4, 26), "wiese.kai@webmail.de",
                new Address("Dr.-Heffner-Straße", "33", "14112", "Berlin", "Deutschland"));
        haseMgmtDriver.addNewCustomer(aCustomer);

    }

    public void setupTestContracts() {
        // creating some ActivityRecords

        // for the contract 100001
        ActivityRecord ar01 = new ActivityRecord(LocalDate.of(2022, 6, 3), LocalTime.of(8, 30), LocalTime.of(13, 0),
                haseMgmtDriver.getAllEmployees().get(0), "Tätigkeit 01");
        ActivityRecord ar02 = new ActivityRecord(LocalDate.of(2022, 6, 11), LocalTime.of(10, 15), LocalTime.of(14, 30),
                haseMgmtDriver.getAllEmployees().get(0), "Tätigkeit 02");

        ActivityRecord ar03 = new ActivityRecord(LocalDate.of(2022, 6, 11), LocalTime.of(10, 15), LocalTime.of(14, 30),
                haseMgmtDriver.getAllEmployees().get(1), "Tätigkeit 02");
        ActivityRecord ar04 = new ActivityRecord(LocalDate.of(2022, 6, 12), LocalTime.of(9, 0), LocalTime.of(11, 30),
                haseMgmtDriver.getAllEmployees().get(1), "Tätigkeit 03");

        // for contract 100002
        ActivityRecord ar05 = new ActivityRecord(LocalDate.of(2022, 7, 17), LocalTime.of(14, 30), LocalTime.of(17, 0),
                haseMgmtDriver.getAllEmployees().get(2), "Tätigkeit 04");
        ActivityRecord ar06 = new ActivityRecord(LocalDate.of(2022, 7, 19), LocalTime.of(9, 30), LocalTime.of(15, 0),
                haseMgmtDriver.getAllEmployees().get(2), "Tätigkeit 07");

        // Employee 1004
        ActivityRecord ar07 = new ActivityRecord(LocalDate.of(2022, 7, 20), LocalTime.of(14, 30), LocalTime.of(17, 0),
                haseMgmtDriver.getAllEmployees().get(2), "Tätigkeit 04");

        ActivityRecord ar08 = new ActivityRecord(LocalDate.of(2022, 8, 2), LocalTime.of(9, 30), LocalTime.of(15, 0),
                haseMgmtDriver.getAllEmployees().get(2), "Tätigkeit 07");

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
                new Address("Theodor-Storm-Straße", "23", "73529", "Berlin", "Deutschland"), haseMgmtDriver.getAllCustomers().get(0),
                haseMgmtDriver.getAllEmployees().get(0), "Warung", "fertiggestsellt",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.", arList01);
        haseMgmtDriver.addNewContract(aContract);

        aContract = new Contract(LocalDate.of(2022, 2, 10),
                new Address("Theodor-Storm-Straße", "23", "73529", "Berlin", "Deutschland"), haseMgmtDriver.getAllCustomers().get(0),
                haseMgmtDriver.getAllEmployees().get(0), "Warung", "fertiggestsellt",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.", arList02);
        haseMgmtDriver.addNewContract(aContract);
    }
}
