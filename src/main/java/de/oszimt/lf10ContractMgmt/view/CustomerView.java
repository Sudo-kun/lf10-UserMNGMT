package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.util.FontUtil;

import javax.swing.*;
import java.awt.*;

public class CustomerView extends JPanel {

    public static final String VIEW_TITLE = "Edit/Create Customer";

    public CustomerView() {
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
        JLabel firstNameLabel = new JLabel("Firstname:");
        JLabel lastNameLabel = new JLabel("Lastname:");
        JLabel emailLabel = new JLabel("E-Mail:");
        JLabel birthdayLabel = new JLabel("Birthday:");
        JLabel addressLabel = new JLabel("Address:");

        // Create JTextFields for the form fields
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField birthdayField = new JTextField();

        JTextField addressStreetField = new JTextField();
        JTextField addressNumberField = new JTextField();
        JTextField addressPostalCodeField = new JTextField();
        JTextField addressCityField = new JTextField();
        JTextField addressCountryField = new JTextField();

        // Add the JLabels and JTextFields to the formPanel
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        formPanel.add(emailLabel);
        formPanel.add(emailField);

        formPanel.add(birthdayLabel);
        formPanel.add(birthdayField);

        formPanel.add(addressLabel);
        formPanel.add(addressStreetField);
        //formPanel.add(addressNumberField);
        //formPanel.add(addressPostalCodeField);
        //formPanel.add(addressCityField);
        //formPanel.add(addressCountryField);

        var selectBtn = new Button("Select");
        var saveBtn = new Button("Save");

        var buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        var buttonContainerGrid = new JPanel(new GridLayout(1, 3, 25, 25));
        buttonContainerGrid.add(selectBtn);
        buttonContainerGrid.add(Box.createVerticalGlue());
        buttonContainerGrid.add(saveBtn);
        buttonContainer.add(buttonContainerGrid);

        formContainer.add(Box.createHorizontalGlue());
        formContainer.add(formPanel);
        formContainer.add(Box.createHorizontalGlue());

        parentContainer.add(Box.createHorizontalGlue());
        parentContainer.add(formContainer);
        parentContainer.add(buttonContainer);
        parentContainer.add(Box.createHorizontalGlue());
        add(parentContainer);
    }

}
