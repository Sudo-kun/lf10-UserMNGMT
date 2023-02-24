package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.ActivityRecord;
import de.oszimt.lf10ContractMgmt.model.Contract;
import de.oszimt.lf10ContractMgmt.util.FontUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ActivityDetailsView extends JPanel {
    public ActivityDetailsView(Contract contract) {
        setupWindow();
        setupActivityDetailsView(contract);
        setVisible(true);
    }

    private void setupActivityDetailsView(Contract contract) {
        setLayout(new BorderLayout());

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));

        JPanel inputsPanel = createInputsPanel(contract);
        mainCenterPanel.add(inputsPanel);

        JLabel titleForTaskList = new JLabel("Tasks");

        titleForTaskList.setFont(FontUtil.getBoldFont());
        titleForTaskList.setHorizontalAlignment(SwingConstants.LEADING);
        mainCenterPanel.add(titleForTaskList);

        JPanel taskListPanel = createTaskListPanel(contract.getActivityRecordList());
        mainCenterPanel.add(taskListPanel);

        JButton addNewTaskButton = new JButton("Add new task");
        mainCenterPanel.add(addNewTaskButton);

        add(mainCenterPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = createButtonsPanel();
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        addPaddingToMainWindow();

        setSize(1920, 1080);
    }

    private JPanel createInputsPanel(Contract contract) {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2));
        inputPanel.setPreferredSize(new Dimension(700, 330));
        inputPanel.setMaximumSize(new Dimension(700, 330));
        inputPanel.setMinimumSize(new Dimension(700, 330));

        JLabel addressLabel = new JLabel("Project location");
        JTextField streetInput = new JTextField(contract.getProjectLocations().getStreet());
        JTextField streetNumberInput = new JTextField(contract.getProjectLocations().getHouse());
        JPanel addressPanel1 = new JPanel();

        streetInput.setPreferredSize(new Dimension(200, 25));
        streetNumberInput.setPreferredSize(new Dimension(100, 25));
        addressPanel1.setLayout(new FlowLayout());

        addressPanel1.add(streetInput);
        addressPanel1.add(streetNumberInput);

        JTextField postalCodeInput = new JTextField(contract.getProjectLocations().getPostalCode());
        JTextField cityInput = new JTextField(contract.getProjectLocations().getCity());
        JPanel addressPanel2 = new JPanel();

        cityInput.setPreferredSize(new Dimension(200, 25));
        postalCodeInput.setPreferredSize(new Dimension(100, 25));

        addressPanel2.setLayout(new FlowLayout());
        addressPanel2.add(postalCodeInput);
        addressPanel2.add(cityInput);

        JPanel addressPanel3 = new JPanel();
        JTextField countryInput = new JTextField(contract.getProjectLocations().getCountry());
        countryInput.setPreferredSize(new Dimension(305, 25));
        addressPanel3.add(countryInput);

        JComboBox<String> customerSelect = new JComboBox<>();
        customerSelect.setPreferredSize(new Dimension(305, 25));
        JPanel customerSelectWrapper = new JPanel();
        customerSelectWrapper.add(customerSelect);

        JComboBox<String> employeeSelect = new JComboBox<>();
        employeeSelect.setPreferredSize(new Dimension(305, 25));
        JPanel employeeSelectWrapper = new JPanel();
        employeeSelectWrapper.add(employeeSelect);

        JComboBox<String> typeSelect = new JComboBox<>();
        typeSelect.setPreferredSize(new Dimension(305, 25));
        JPanel typeSelectWrapper = new JPanel();
        typeSelectWrapper.add(typeSelect);

        JComboBox<String> stateSelect = new JComboBox<>();
        stateSelect.setPreferredSize(new Dimension(305, 25));
        JPanel stateSelectWrapper = new JPanel();
        stateSelectWrapper.add(stateSelect);


        JTextField descriptionInput = new JTextField(contract.getDescription());
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

    private JPanel createTaskListPanel(ArrayList<ActivityRecord> activityRecords) {
        JPanel taskListPanel = new JPanel();
        taskListPanel.setPreferredSize(new Dimension(700, 40 + 40 * activityRecords.size()));
        taskListPanel.setMaximumSize(new Dimension(700, 40 + 40 * activityRecords.size()));
        taskListPanel.setMinimumSize(new Dimension(700, 40 + 40 * activityRecords.size()));
        taskListPanel.setLayout(new GridLayout(1 + activityRecords.size(), 4));
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

        addTasksToTaskList(taskListPanel, activityRecords);

        return taskListPanel;
    }

    private void addTasksToTaskList(JPanel taskListPanel, ArrayList<ActivityRecord> activityRecords) {
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
            taskActionsButtonPanel.add(new JButton("Edit"));
            taskActionsButtonPanel.add(new JButton("Delete"));

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
}
