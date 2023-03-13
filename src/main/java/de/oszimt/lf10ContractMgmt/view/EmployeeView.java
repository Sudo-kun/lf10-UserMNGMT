package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.Address;
import de.oszimt.lf10ContractMgmt.model.Employee;
import de.oszimt.lf10ContractMgmt.util.FontUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JPanel {

    public static final String VIEW_TITLE = "Mitarbeiter erstellen/bearbeiten";

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField telephoneField;

    private JTextField addressStreetField;
    private JTextField addressNumberField;
    private JTextField addressPostalCodeField;
    private JTextField addressCityField;
    private JTextField addressCountryField;

    private JButton saveButton = new JButton("Speichern");


    public EmployeeView(Employee employee, HaseGmbHManagement haseGmbHManagement) {
        setLayout(new GridLayout(3, 1));

        var titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(VIEW_TITLE);

        titleLabel.setFont(FontUtil.getBoldFont(20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);

        add(titlePanel);

        var parentContainer = new JPanel();
        parentContainer.setLayout(new BoxLayout(parentContainer, BoxLayout.Y_AXIS));

        var formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.X_AXIS));

        // Create a JPanel with a GridLayout of 5 rows and 2 columns
        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        // Create 5 JLabels for the captions
        JLabel firstNameLabel = new JLabel("Vorname:");
        JLabel lastNameLabel = new JLabel("Nachname:");
        JLabel emailLabel = new JLabel("E-Mail:");
        JLabel telephoneLabel = new JLabel("Telefon:");
        JLabel addressLabel = new JLabel("Adresse:");

        // Create JTextFields for the form fields
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        telephoneField = new JTextField();

        addressStreetField = new JTextField();
        addressNumberField = new JTextField();
        addressPostalCodeField = new JTextField();
        addressCityField = new JTextField();
        addressCountryField = new JTextField();


        // Add the JLabels and JTextFields to the formPanel
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        formPanel.add(emailLabel);
        formPanel.add(emailField);

        formPanel.add(telephoneLabel);
        formPanel.add(telephoneField);

        var addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.X_AXIS));

        addressPanel.add(addressStreetField);
        addressPanel.add(addressNumberField);
        addressPanel.add(addressPostalCodeField);
        addressPanel.add(addressCityField);
        addressPanel.add(addressCountryField);

        formPanel.add(addressLabel);
        formPanel.add(addressPanel);

        saveButton.addActionListener(e -> {
            createOrUpdateEmployee(haseGmbHManagement, employee);
        });

        var buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        var buttonContainerGrid = new JPanel(new GridLayout(1, 3, 25, 25));
        buttonContainerGrid.add(saveButton);
        buttonContainer.add(buttonContainerGrid);

        formContainer.add(Box.createHorizontalGlue());
        formContainer.add(formPanel);
        formContainer.add(Box.createHorizontalGlue());

        parentContainer.add(Box.createHorizontalGlue());
        parentContainer.add(formContainer);
        parentContainer.add(buttonContainer);
        parentContainer.add(Box.createHorizontalGlue());
        add(parentContainer);

        if (employee != null)
            loadDataByEmployee(employee);
    }

    private void loadDataByEmployee(Employee employee) {
        firstNameField.setText(employee.getFirstname());
        lastNameField.setText(employee.getLastname());
        emailField.setText(employee.getEmail());
        telephoneField.setText(employee.getTelephone());
        addressStreetField.setText(employee.getAddress().getStreet());
        addressNumberField.setText(employee.getAddress().getHouse());
        addressPostalCodeField.setText(employee.getAddress().getPostalCode());
        addressCityField.setText(employee.getAddress().getCity());
        addressCountryField.setText(employee.getAddress().getCountry());
    }

    private void createOrUpdateEmployee(HaseGmbHManagement haseGmbHManagement, Employee employee) {
        String firstname = firstNameField.getText();
        String lastname = lastNameField.getText();
        String email = emailField.getText();
        String telephone = telephoneField.getText();
        String addressStreet = addressStreetField.getText();
        String addressNumber = addressNumberField.getText();
        String addressPostalCode = addressPostalCodeField.getText();
        String addressCity = addressCityField.getText();
        String addressCountry = addressCountryField.getText();

        if (employee == null) {
            employee = new Employee(firstname, lastname, new Address(addressStreet, addressNumber, addressPostalCode, addressCity, addressCountry), email, telephone);

            haseGmbHManagement.addNewEmployee(employee);
        } else {
            employee.setFirstname(firstname);
            employee.setLastname(lastname);
            employee.setEmail(email);
            employee.setTelephone(telephone);
            employee.setAddress(new Address(addressStreet, addressNumber, addressPostalCode, addressCity, addressCountry));
        }
    }


    public void setSaveButtionActionListener(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
    }
}
