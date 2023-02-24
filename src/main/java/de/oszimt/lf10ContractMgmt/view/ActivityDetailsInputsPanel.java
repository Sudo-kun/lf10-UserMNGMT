package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.Contract;
import de.oszimt.lf10ContractMgmt.model.Customer;
import de.oszimt.lf10ContractMgmt.model.Employee;

import javax.swing.*;
import java.awt.*;

public class ActivityDetailsInputsPanel extends JPanel {
    private JTextField streetInput;
    private JTextField streetNumberInput;
    private JTextField postalCodeInput;
    private JTextField cityInput;
    private JTextField countryInput;
    private JTextField stateInput;
    private JTextField typeInput;
    private JComboBox<Customer> customerSelect;
    private JComboBox<Employee> employeeSelect;
    private JTextField descriptionInput;

    public ActivityDetailsInputsPanel(Contract contract, HaseGmbHManagement haseGmbHManagement) {
        // get values / default values if contract is null
        String street = contract == null ? "" : contract.getProjectLocations().getStreet();
        String house = contract == null ? "" : contract.getProjectLocations().getHouse();
        String postalCode = contract == null ? "" : contract.getProjectLocations().getPostalCode();
        String city = contract == null ? "" : contract.getProjectLocations().getCity();
        String country = contract == null ? "" : contract.getProjectLocations().getCountry();
        Customer customer = contract == null ? null : contract.getCustomer();
        Employee projectOwner = contract == null ? null : contract.getProjectOwner();
        String type = contract == null ? "" : contract.getContractType();
        String state = contract == null ? "" : contract.getState();
        String description = contract == null ? "" : contract.getDescription();

        setupPanelSettings();

        addInputFields(haseGmbHManagement, street, house, postalCode, city, country, customer, projectOwner, type, state, description);
    }

    private void setupPanelSettings() {
        setLayout(new GridLayout(8, 2));
        setPreferredSize(new Dimension(700, 330));
        setMaximumSize(new Dimension(700, 330));
        setMinimumSize(new Dimension(700, 330));
    }

    private void addInputFields(HaseGmbHManagement haseGmbHManagement, String street, String house, String postalCode, String city, String country, Customer customer, Employee projectOwner, String type, String state, String description) {

        JLabel addressLabel = new JLabel("Project location");
        streetInput = new JTextField(street);
        streetNumberInput = new JTextField(house);
        JPanel addressPanel1 = new JPanel();

        streetInput.setPreferredSize(new Dimension(200, 25));
        streetNumberInput.setPreferredSize(new Dimension(100, 25));
        addressPanel1.setLayout(new FlowLayout());

        addressPanel1.add(streetInput);
        addressPanel1.add(streetNumberInput);

        postalCodeInput = new JTextField(postalCode);
        cityInput = new JTextField(city);
        JPanel addressPanel2 = new JPanel();

        cityInput.setPreferredSize(new Dimension(200, 25));
        postalCodeInput.setPreferredSize(new Dimension(100, 25));

        addressPanel2.setLayout(new FlowLayout());
        addressPanel2.add(postalCodeInput);
        addressPanel2.add(cityInput);

        JPanel addressPanel3 = new JPanel();
        countryInput = new JTextField(country);
        countryInput.setPreferredSize(new Dimension(305, 25));
        addressPanel3.add(countryInput);

        customerSelect = new JComboBox<>(haseGmbHManagement.getAllCustomers().toArray(new Customer[0]));
        customerSelect.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Customer customer = ((Customer) value);
                String displayValue = "Customer: " + customer.getFirstname() + " " + customer.getLastname();

                return super.getListCellRendererComponent(list, displayValue, index, isSelected, cellHasFocus);
            }
        });
        customerSelect.setPreferredSize(new Dimension(305, 25));
        if (customer != null) customerSelect.setSelectedItem(customer);

        JPanel customerSelectWrapper = new JPanel();
        customerSelectWrapper.add(customerSelect);

        employeeSelect = new JComboBox<>(haseGmbHManagement.getAllEmployees().toArray(new Employee[0]));
        employeeSelect.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Employee employee = ((Employee) value);
                String displayValue = "Employee: " + employee.getFirstname() + " " + employee.getLastname();

                return super.getListCellRendererComponent(list, displayValue, index, isSelected, cellHasFocus);
            }
        });
        employeeSelect.setPreferredSize(new Dimension(305, 25));
        if (projectOwner != null) employeeSelect.setSelectedItem(projectOwner);

        JPanel employeeSelectWrapper = new JPanel();
        employeeSelectWrapper.add(employeeSelect);

        typeInput = new JTextField(type);
        typeInput.setPreferredSize(new Dimension(305, 25));
        JPanel typeSelectWrapper = new JPanel();
        typeSelectWrapper.add(typeInput);

        stateInput = new JTextField(state);
        stateInput.setPreferredSize(new Dimension(305, 25));
        JPanel stateSelectWrapper = new JPanel();
        stateSelectWrapper.add(stateInput);


        descriptionInput = new JTextField(description);
        descriptionInput.setPreferredSize(new Dimension(305, 25));
        JPanel descriptionInputWrapper = new JPanel();
        descriptionInputWrapper.add(descriptionInput);


        add(addressLabel);
        add(addressPanel1);
        add(new JLabel());
        add(addressPanel2);
        add(new JLabel());
        add(addressPanel3);
        add(new JLabel("Select Customer"));
        add(customerSelectWrapper);
        add(new JLabel("Select Employee"));
        add(employeeSelectWrapper);
        add(new JLabel("Select Type"));
        add(typeSelectWrapper);
        add(new JLabel("Select State"));
        add(stateSelectWrapper);
        add(new JLabel("Description"));
        add(descriptionInputWrapper);
    }

    public JTextField getStreetInput() {
        return streetInput;
    }

    public JTextField getStreetNumberInput() {
        return streetNumberInput;
    }

    public JTextField getPostalCodeInput() {
        return postalCodeInput;
    }

    public JTextField getCityInput() {
        return cityInput;
    }

    public JTextField getCountryInput() {
        return countryInput;
    }

    public JTextField getStateInput() {
        return stateInput;
    }

    public JTextField getTypeInput() {
        return typeInput;
    }

    public JComboBox<Customer> getCustomerSelect() {
        return customerSelect;
    }

    public JComboBox<Employee> getEmployeeSelect() {
        return employeeSelect;
    }

    public JTextField getDescriptionInput() {
        return descriptionInput;
    }
}
