package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.Address;
import de.oszimt.lf10ContractMgmt.model.Customer;
import de.oszimt.lf10ContractMgmt.util.FontUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerView extends JPanel {

    public static final String VIEW_TITLE = "Edit/Create Customer";

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField birthdayField;

    private JTextField addressStreetField;
    private JTextField addressNumberField;
    private JTextField addressPostalCodeField;
    private JTextField addressCityField;
    private JTextField addressCountryField;

    private JButton saveButton = new JButton("Speichern");

    public CustomerView(Customer customer, HaseGmbHManagement haseGmbHManagement) {
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
        JLabel birthdayLabel = new JLabel("Geburtstag:");
        JLabel addressLabel = new JLabel("Adresse:");

        // Create JTextFields for the form fields
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        birthdayField = new JTextField();

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

        formPanel.add(birthdayLabel);
        formPanel.add(birthdayField);

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
            createOrUpdateCustomer(haseGmbHManagement, customer);
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

        if (customer != null)
            loadDataByCustomer(customer);
    }

    private void loadDataByCustomer(Customer customer) {
        firstNameField.setText(customer.getFirstname());
        lastNameField.setText(customer.getLastname());
        emailField.setText(customer.getEmail());
        birthdayField.setText(customer.getBirthday().format(DateTimeFormatter.ISO_DATE));
        addressStreetField.setText(customer.getAddress().getStreet());
        addressNumberField.setText(customer.getAddress().getHouse());
        addressPostalCodeField.setText(customer.getAddress().getPostalCode());
        addressCityField.setText(customer.getAddress().getCity());
        addressCountryField.setText(customer.getAddress().getCountry());
    }

    private void createOrUpdateCustomer(HaseGmbHManagement haseGmbHManagement, Customer customer) {
        String firstname = firstNameField.getText();
        String lastname = lastNameField.getText();
        String email = emailField.getText();
        String birthday = birthdayField.getText();
        String addressStreet = addressStreetField.getText();
        String addressNumber = addressNumberField.getText();
        String addressPostalCode = addressPostalCodeField.getText();
        String addressCity = addressCityField.getText();
        String addressCountry = addressCountryField.getText();

        if (customer == null) {
            customer = new Customer(firstname, lastname, LocalDate.parse(birthday), email, new Address(addressStreet, addressNumber, addressPostalCode, addressCity, addressCountry));

            haseGmbHManagement.addNewCustomer(customer);
        } else {
            customer.setFirstname(firstname);
            customer.setLastname(lastname);
            customer.setEmail(email);
            customer.setBirthday(LocalDate.parse(birthday));
            customer.setAddress(new Address(addressStreet, addressNumber, addressPostalCode, addressCity, addressCountry));
        }
    }

    public void setSaveButtionActionListener(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
    }
}
