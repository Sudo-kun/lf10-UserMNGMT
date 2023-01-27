package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.ActivityRecord;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ActivityDetailsView extends JFrame {
    public ActivityDetailsView() {
        super("Activity Details");

        setupWindow();
        setupActivityDetailsView();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupActivityDetailsView() {
        getContentPane().setLayout(new BorderLayout());

        JLabel title = new JLabel("Activity Details");
        getContentPane().add(title, BorderLayout.NORTH);

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));

        JPanel inputsPanel = createInputsPanel();
        mainCenterPanel.add(inputsPanel);

        JLabel titleForTaskList = new JLabel("Activity Details");
        mainCenterPanel.add(titleForTaskList);

        JPanel taskListPanel = createTaskListPanel(createMockTaskList());
        mainCenterPanel.add(taskListPanel);

        getContentPane().add(mainCenterPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = createButtonsPanel();
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        addPaddingToMainWindow();

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createInputsPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2));
        inputPanel.setPreferredSize(new Dimension(500, 330));
        inputPanel.setMaximumSize(new Dimension(500, 330));
        inputPanel.setMinimumSize(new Dimension(500, 330));

        JLabel addressLabel = new JLabel("Project location");
        JTextField streetInput = new JTextField();
        JTextField streetNumberInput = new JTextField();
        JPanel addressPanel1 = new JPanel();

        streetInput.setPreferredSize(new Dimension(100, 25));
        streetNumberInput.setPreferredSize(new Dimension(100, 25));
        addressPanel1.setLayout(new FlowLayout());

        addressPanel1.add(streetInput);
        addressPanel1.add(streetNumberInput);

        JTextField postalCodeInput = new JTextField();
        JTextField cityInput = new JTextField();
        JPanel addressPanel2 = new JPanel();

        cityInput.setPreferredSize(new Dimension(100, 25));
        postalCodeInput.setPreferredSize(new Dimension(100, 25));

        addressPanel2.setLayout(new FlowLayout());
        addressPanel2.add(postalCodeInput);
        addressPanel2.add(cityInput);

        JPanel addressPanel3 = new JPanel();
        JTextField countryInput = new JTextField();
        countryInput.setPreferredSize(new Dimension(205, 25));
        addressPanel3.add(countryInput);

        JComboBox<String> customerSelect = new JComboBox<String>();
        customerSelect.setPreferredSize(new Dimension(205, 25));
        JPanel customerSelectWrapper = new JPanel();
        customerSelectWrapper.add(customerSelect);

        JComboBox<String> employeeSelect = new JComboBox<String>();
        employeeSelect.setPreferredSize(new Dimension(205, 25));
        JPanel employeeSelectWrapper = new JPanel();
        employeeSelectWrapper.add(employeeSelect);

        JComboBox<String> typeSelect = new JComboBox<String>();
        typeSelect.setPreferredSize(new Dimension(205, 25));
        JPanel typeSelectWrapper = new JPanel();
        typeSelectWrapper.add(typeSelect);

        JComboBox<String> stateSelect = new JComboBox<String>();
        stateSelect.setPreferredSize(new Dimension(205, 25));
        JPanel stateSelectWrapper = new JPanel();
        stateSelectWrapper.add(stateSelect);


        JTextField descriptionInput = new JTextField();
        descriptionInput.setPreferredSize(new Dimension(205, 25));
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
        taskListPanel.setPreferredSize(new Dimension(500, 40 + 40 * activityRecords.size()));
        taskListPanel.setMaximumSize(new Dimension(500, 40 + 40 * activityRecords.size()));
        taskListPanel.setMinimumSize(new Dimension(500, 40 + 40 * activityRecords.size()));
        taskListPanel.setLayout(new GridLayout(1 + activityRecords.size(), 4));
        taskListPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        JLabel timeLabel = new JLabel("Start - & Endtime");
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timeLabel.setPreferredSize(new Dimension(100, 25));

        JLabel employeeCountLabel = new JLabel("Employee");
        employeeCountLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        employeeCountLabel.setPreferredSize(new Dimension(100, 25));

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionLabel.setPreferredSize(new Dimension(100, 25));

        JLabel actionsLabel = new JLabel("Actions");
        actionsLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        actionsLabel.setPreferredSize(new Dimension(100, 25));

        taskListPanel.add(timeLabel);
        taskListPanel.add(employeeCountLabel);
        taskListPanel.add(descriptionLabel);
        taskListPanel.add(actionsLabel);

        addTasksToTaskList(taskListPanel, activityRecords);

        return taskListPanel;
    }

    private void addTasksToTaskList(JPanel taskListPanel, ArrayList<ActivityRecord> activityRecords) {
        for (ActivityRecord activityRecord : activityRecords) {
            JLabel timeText = new JLabel(activityRecord.getStartTime() + "-" + activityRecord.getEndTime());
            timeText.setBackground(Color.lightGray);
            timeText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            timeText.setPreferredSize(new Dimension(100, 25));

            JLabel employeeCountText = new JLabel("Employee");
            employeeCountText.setBackground(Color.lightGray);
            employeeCountText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            employeeCountText.setPreferredSize(new Dimension(100, 25));

            JLabel descriptionText = new JLabel("Description");
            descriptionText.setBackground(Color.lightGray);
            descriptionText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            descriptionText.setPreferredSize(new Dimension(100, 25));

            JLabel actionsText = new JLabel("Actions");
            actionsText.setBackground(Color.lightGray);
            actionsText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            actionsText.setPreferredSize(new Dimension(100, 25));

            taskListPanel.add(timeText);
            taskListPanel.add(employeeCountText);
            taskListPanel.add(descriptionText);
            taskListPanel.add(actionsText);
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
        JPanel contentPanel = new JPanel();

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        contentPanel.setBorder(padding);

        this.setContentPane(contentPanel);
    }

    // TODO remove after adding backend
    private ArrayList<ActivityRecord> createMockTaskList() {
        ArrayList<ActivityRecord> activityRecords = new ArrayList<>();

        activityRecords.add(new ActivityRecord(LocalDateTime.now(), LocalDateTime.now(), 4, "text"));
        activityRecords.add(new ActivityRecord(LocalDateTime.now(), LocalDateTime.now(), 6, "text123"));
        activityRecords.add(new ActivityRecord(LocalDateTime.now(), LocalDateTime.now(), 5, "321text"));

        return activityRecords;
    }
}
