package de.oszimt.lf10ContractMgmt.view.activity.details;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.Address;
import de.oszimt.lf10ContractMgmt.model.Contract;
import de.oszimt.lf10ContractMgmt.model.Customer;
import de.oszimt.lf10ContractMgmt.model.Employee;
import de.oszimt.lf10ContractMgmt.util.FontUtil;
import de.oszimt.lf10ContractMgmt.view.activity.task.TaskListPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDate;

public class ActivityDetailsView extends JPanel {
    private ActivityDetailsInputsPanel activityDetailsInputsPanel;
    private TaskListPanel taskListPanel;
    JButton saveButton = new JButton("Speichern");
    JLabel titleForActivityDetails = new JLabel("Aktivitätsdetails");


    public ActivityDetailsView(Contract contract, HaseGmbHManagement haseGmbHManagement) {
        setupWindow();
        setupActivityDetailsView(contract, haseGmbHManagement);
        setVisible(true);
    }

    private void setupActivityDetailsView(Contract contract, HaseGmbHManagement haseGmbHManagement) {
        setLayout(new BorderLayout());
        titleForActivityDetails.setFont(FontUtil.getBoldFont(25));

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));

        JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new BoxLayout(headlinePanel, BoxLayout.X_AXIS));

        headlinePanel.add(Box.createHorizontalGlue());
        headlinePanel.add(titleForActivityDetails);
        headlinePanel.add(Box.createHorizontalGlue());

        mainCenterPanel.add(headlinePanel);
        mainCenterPanel.add(Box.createVerticalStrut(29));

        activityDetailsInputsPanel = new ActivityDetailsInputsPanel(contract, haseGmbHManagement);
        mainCenterPanel.add(activityDetailsInputsPanel);

        JLabel titleForTaskList = new JLabel("Aufgaben");

        titleForTaskList.setFont(FontUtil.getBoldFont());
        titleForTaskList.setHorizontalAlignment(SwingConstants.LEADING);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(titleForTaskList);
        titlePanel.add(Box.createHorizontalGlue());

        mainCenterPanel.add(titlePanel);
        taskListPanel = new TaskListPanel(contract, haseGmbHManagement);

        mainCenterPanel.add(taskListPanel);
        mainCenterPanel.add(Box.createVerticalStrut(10));
        mainCenterPanel.add(taskListPanel.createNewTaskButton(haseGmbHManagement));

        add(mainCenterPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = createButtonsPanel(haseGmbHManagement, contract);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(padding);

        setSize(1920, 1080);
    }

    private JPanel createButtonsPanel(HaseGmbHManagement haseGmbHManagement, Contract contract) {
        JPanel buttonsPanel = new JPanel();

        saveButton.setPreferredSize(new Dimension(200, 30));
        saveButton.addActionListener(e -> {
            createOrUpdateContract(haseGmbHManagement, contract);
        });

        buttonsPanel.add(saveButton);

        return buttonsPanel;
    }

    public void setSaveButtonText(String text) {
        saveButton.setText(text);
    }

    private void createOrUpdateContract(HaseGmbHManagement haseGmbHManagement, Contract contract) {
        String street = activityDetailsInputsPanel.getStreetInput().getText();
        String house = activityDetailsInputsPanel.getStreetNumberInput().getText();
        String postalCode = activityDetailsInputsPanel.getPostalCodeInput().getText();
        String city = activityDetailsInputsPanel.getCityInput().getText();
        String country = activityDetailsInputsPanel.getCountryInput().getText();
        Customer customer = (Customer) activityDetailsInputsPanel.getCustomerSelect().getSelectedItem();
        Employee projectOwner = (Employee) activityDetailsInputsPanel.getEmployeeSelect().getSelectedItem();
        String type = activityDetailsInputsPanel.getTypeInput().getText();
        String state = activityDetailsInputsPanel.getStateInput().getText();
        String description = activityDetailsInputsPanel.getDescriptionInput().getText();

        if (contract == null) {
            contract = new Contract(LocalDate.now(), new Address(street, house, postalCode, city, country), customer, projectOwner, type, state, description, taskListPanel.getActivityRecordBufferList());

            haseGmbHManagement.addNewContract(contract);
        } else {
            contract.setProjectLocations(new Address(street, house, postalCode, city, country));
            contract.setCustomer(customer);
            contract.setProjectOwner(projectOwner);
            contract.setContractType(type);
            contract.setState(state);
            contract.setDescription(description);
            contract.setActivityRecordList(taskListPanel.getActivityRecordBufferList());
        }
    }

    public void setHeadlineText(String text) {
        titleForActivityDetails.setText(text);
    }
}
