package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ActivityDetailsView extends JPanel {
    JTextField streetInput;
    JTextField streetNumberInput;
    JTextField postalCodeInput;
    JTextField cityInput;
    JTextField countryInput;
    JTextField stateInput;
    JTextField typeInput;

    JComboBox<Customer> customerSelect;
    JComboBox<Employee> employeeSelect;
    JTextField descriptionInput;
    ArrayList<ActivityRecord> activityRecordBufferList;

    JPanel mainCenterPanel;
    JPanel taskListPanel = new JPanel();


    public ActivityDetailsView(Contract contract, HaseGmbHManagement haseGmbHManagement) {
        if (contract != null) {
            activityRecordBufferList = (ArrayList<ActivityRecord>) contract.getActivityRecordList().clone();
        } else {
            activityRecordBufferList = new ArrayList<>();
        }

        setupWindow();
        setupActivityDetailsView(contract, haseGmbHManagement);
        setVisible(true);
    }

    private void setupActivityDetailsView(Contract contract, HaseGmbHManagement haseGmbHManagement) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Activity Details");

        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));

        JPanel inputsPanel = createInputsPanel(contract, haseGmbHManagement);
        mainCenterPanel.add(inputsPanel);

        JLabel titleForTaskList = new JLabel("Tasks");

        titleForTaskList.setFont(new Font("Serif", Font.BOLD, 20));
        titleForTaskList.setHorizontalAlignment(SwingConstants.LEADING);
        mainCenterPanel.add(titleForTaskList);

        JPanel taskListPanel = createTaskListPanel(haseGmbHManagement);
        mainCenterPanel.add(taskListPanel);


        JButton addNewTaskButton = new JButton("Add new task");
        mainCenterPanel.add(addNewTaskButton);
        addNewTaskButton.addActionListener(e -> {
            new TaskDetailsView(null, haseGmbHManagement, activityRecordBufferList, this);
        });

        add(mainCenterPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = createButtonsPanel();
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        addPaddingToMainWindow();

        setSize(500, 500);
    }

    private JPanel createInputsPanel(Contract contract, HaseGmbHManagement haseGmbHManagement) {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2));
        inputPanel.setPreferredSize(new Dimension(700, 330));
        inputPanel.setMaximumSize(new Dimension(700, 330));
        inputPanel.setMinimumSize(new Dimension(700, 330));

        JLabel addressLabel = new JLabel("Project location");
        streetInput = new JTextField(contract.getProjectLocations().getStreet());
        streetNumberInput = new JTextField(contract.getProjectLocations().getHouse());
        JPanel addressPanel1 = new JPanel();

        streetInput.setPreferredSize(new Dimension(200, 25));
        streetNumberInput.setPreferredSize(new Dimension(100, 25));
        addressPanel1.setLayout(new FlowLayout());

        addressPanel1.add(streetInput);
        addressPanel1.add(streetNumberInput);

        postalCodeInput = new JTextField(contract.getProjectLocations().getPostalCode());
        cityInput = new JTextField(contract.getProjectLocations().getCity());
        JPanel addressPanel2 = new JPanel();

        cityInput.setPreferredSize(new Dimension(200, 25));
        postalCodeInput.setPreferredSize(new Dimension(100, 25));

        addressPanel2.setLayout(new FlowLayout());
        addressPanel2.add(postalCodeInput);
        addressPanel2.add(cityInput);

        JPanel addressPanel3 = new JPanel();
        countryInput = new JTextField(contract.getProjectLocations().getCountry());
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

        JPanel employeeSelectWrapper = new JPanel();
        employeeSelectWrapper.add(employeeSelect);

        JTextField typeInput = new JTextField();
        typeInput.setPreferredSize(new Dimension(305, 25));
        JPanel typeSelectWrapper = new JPanel();
        typeSelectWrapper.add(typeInput);

        JTextField stateInput = new JTextField();
        stateInput.setPreferredSize(new Dimension(305, 25));
        JPanel stateSelectWrapper = new JPanel();
        stateSelectWrapper.add(stateInput);


        descriptionInput = new JTextField(contract.getDescription());
        descriptionInput.setPreferredSize(new Dimension(305, 25));
        JPanel descriptionInputWrapper = new JPanel();
        descriptionInputWrapper.add(descriptionInput);


        inputPanel.add(addressLabel);
        inputPanel.add(addressPanel1);
        inputPanel.add(new JLabel());
        inputPanel.add(addressPanel2);
        inputPanel.add(new JLabel());
        inputPanel.add(addressPanel3);
        inputPanel.add(new JLabel("Select Customer"));
        inputPanel.add(customerSelectWrapper);
        inputPanel.add(new JLabel("Select Employee"));
        inputPanel.add(employeeSelectWrapper);
        inputPanel.add(new JLabel("Select Type"));
        inputPanel.add(typeSelectWrapper);
        inputPanel.add(new JLabel("Select State"));
        inputPanel.add(stateSelectWrapper);
        inputPanel.add(new JLabel("Description"));
        inputPanel.add(descriptionInputWrapper);

        return inputPanel;
    }

    private JPanel createTaskListPanel(HaseGmbHManagement haseGmbHManagement) {
        taskListPanel.setPreferredSize(new Dimension(700, 40 + 40 * activityRecordBufferList.size()));
        taskListPanel.setMaximumSize(new Dimension(700, 40 + 40 * activityRecordBufferList.size()));
        taskListPanel.setMinimumSize(new Dimension(700, 40 + 40 * activityRecordBufferList.size()));
        taskListPanel.setLayout(new GridLayout(1 + activityRecordBufferList.size(), 4));
        taskListPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        JLabel timeLabel = new JLabel("Start - & Endtime", SwingConstants.CENTER);
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timeLabel.setBackground(Color.LIGHT_GRAY);
        timeLabel.setOpaque(true);
        timeLabel.setPreferredSize(new Dimension(100, 25));

        JLabel employeeLabel = new JLabel("Employee", SwingConstants.CENTER);
        employeeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        employeeLabel.setBackground(Color.LIGHT_GRAY);
        employeeLabel.setOpaque(true);
        employeeLabel.setPreferredSize(new Dimension(100, 25));

        JLabel descriptionLabel = new JLabel("Description", SwingConstants.CENTER);
        descriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionLabel.setBackground(Color.LIGHT_GRAY);
        descriptionLabel.setOpaque(true);
        descriptionLabel.setPreferredSize(new Dimension(100, 25));

        JLabel actionsLabel = new JLabel("Actions", SwingConstants.CENTER);
        actionsLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        actionsLabel.setBackground(Color.LIGHT_GRAY);
        actionsLabel.setOpaque(true);
        actionsLabel.setPreferredSize(new Dimension(100, 25));

        taskListPanel.add(timeLabel);
        taskListPanel.add(employeeLabel);
        taskListPanel.add(descriptionLabel);
        taskListPanel.add(actionsLabel);

        addTasksToTaskList(taskListPanel, activityRecordBufferList, haseGmbHManagement);

        return taskListPanel;
    }

    private void addTasksToTaskList(JPanel taskListPanel, ArrayList<ActivityRecord> activityRecords, HaseGmbHManagement haseGmbHManagement) {
        for (ActivityRecord activityRecord : activityRecords) {
            JLabel timeText = new JLabel(activityRecord.getDate() + " " + activityRecord.getStartTime() + "-" + activityRecord.getEndTime(), SwingConstants.CENTER);
            timeText.setBackground(Color.lightGray);
            timeText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            timeText.setPreferredSize(new Dimension(100, 25));

            JLabel employeeNameText = new JLabel(activityRecord.getEmployee().getFirstname() + " " + activityRecord.getEmployee().getLastname(), SwingConstants.CENTER);
            employeeNameText.setBackground(Color.lightGray);
            employeeNameText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            employeeNameText.setPreferredSize(new Dimension(100, 25));

            JLabel descriptionText = new JLabel(activityRecord.getDescription(), SwingConstants.CENTER);
            descriptionText.setBackground(Color.lightGray);
            descriptionText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            descriptionText.setPreferredSize(new Dimension(100, 25));


            JPanel taskActionsButtonPanel = new JPanel();
            taskActionsButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            taskActionsButtonPanel.setLayout(new FlowLayout());

            JButton editTaskButton = new JButton("Edit");
            JButton deleteTaskButton = new JButton("Delete");
            taskActionsButtonPanel.add(editTaskButton);
            taskActionsButtonPanel.add(deleteTaskButton);

            deleteTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeTask(activityRecord);
                    reloadTaskList(haseGmbHManagement);
                }
            });

            editTaskButton.addActionListener(e -> {
                new TaskDetailsView(activityRecord, haseGmbHManagement, activityRecordBufferList, this);
            });

            taskListPanel.add(timeText);
            taskListPanel.add(employeeNameText);
            taskListPanel.add(descriptionText);
            taskListPanel.add(taskActionsButtonPanel);
        }
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200, 30));

        buttonsPanel.add(saveButton);

        return buttonsPanel;
    }

    private void addPaddingToMainWindow() {
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setBorder(padding);
    }

    private void createOrUpdateContract(HaseGmbHManagement haseGmbHManagement, Contract contract) {
        String street = streetInput.getText();
        String house = streetNumberInput.getText();
        String postalCode = postalCodeInput.getText();
        String city = cityInput.getText();
        String country = countryInput.getText();
        Customer customer = (Customer) customerSelect.getSelectedItem();
        Employee projectOwner = (Employee) employeeSelect.getSelectedItem();
        String type = typeInput.getText();
        String state = stateInput.getText();
        String description = descriptionInput.getText();

        if (contract == null) {
            contract = new Contract(
                    LocalDate.now(), new Address(street, house, postalCode, city, country), customer, projectOwner, type, state, description, activityRecordBufferList
            );

            haseGmbHManagement.addNewContract(contract);
        } else {
            contract.setProjectLocations(new Address(street, house, postalCode, city, country));
            contract.setCustomer(customer);
            contract.setProjectOwner(projectOwner);
            contract.setContractType(type);
            contract.setState(state);
            contract.setDescription(description);
            contract.setActivityRecordList(activityRecordBufferList);
        }
    }

    private void removeTask(ActivityRecord activityRecord) {
        activityRecordBufferList.remove(activityRecord);
    }

    public void reloadTaskList(HaseGmbHManagement haseGmbHManagement) {
        taskListPanel.removeAll();
        createTaskListPanel(haseGmbHManagement);

        taskListPanel.repaint();
        taskListPanel.revalidate();
    }
}
