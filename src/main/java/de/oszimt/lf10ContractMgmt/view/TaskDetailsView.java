package de.oszimt.lf10ContractMgmt.view;


import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.ActivityRecord;
import de.oszimt.lf10ContractMgmt.model.Contract;
import de.oszimt.lf10ContractMgmt.model.Employee;
import de.oszimt.lf10ContractMgmt.util.DateUtils;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class TaskDetailsView extends JPanel {

    private JXDatePicker datePicker;
    private JSpinner startTimeInput;
    private JSpinner endTimeInput;
    private JComboBox<Employee> employeeSelect;
    private JTextField descriptionInput;

    public TaskDetailsView(ActivityRecord activityRecord, HaseGmbHManagement haseGmbHManagement, Contract contract) {
        setupWindow();
        setupActivityDetailsView(activityRecord, haseGmbHManagement, contract);
        setVisible(true);
    }

    private void setupActivityDetailsView(ActivityRecord activityRecord, HaseGmbHManagement haseGmbHManagement, Contract contract) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Task Details");

        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel inputsPanel = createInputsPanel(activityRecord, haseGmbHManagement);
        add(inputsPanel);

        JPanel buttonsPanel = createButtonsPanel(haseGmbHManagement, activityRecord, contract);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        addPaddingToMainWindow();

        setSize(500, 500);
    }

    private JPanel createInputsPanel(ActivityRecord activityRecord, HaseGmbHManagement haseGmbHManagement) {
        Date date = activityRecord == null ? DateUtils.asDate(LocalDate.now()) : DateUtils.asDate(activityRecord.getDate());
        Date startTime = activityRecord == null ? DateUtils.asDate(LocalDateTime.now()) : DateUtils.asDateTimeIgnoringDate(activityRecord.getStartTime());
        Date endTime = activityRecord == null ? DateUtils.asDate(LocalDateTime.now()) : DateUtils.asDateTimeIgnoringDate(activityRecord.getEndTime());
        String description = activityRecord == null ? "" : activityRecord.getDescription();
        Employee selectedEmployee = activityRecord == null ? null : activityRecord.getEmployee();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2));
        inputPanel.setPreferredSize(new Dimension(700, 330));
        inputPanel.setMaximumSize(new Dimension(700, 330));
        inputPanel.setMinimumSize(new Dimension(700, 330));

        JLabel dateLabel = new JLabel("Date");
        datePicker = new JXDatePicker();
        datePicker.setDate(date);

        JLabel startTimeLabel = new JLabel("Start time");
        startTimeInput = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeInput, "HH:mm");
        startTimeInput.setEditor(startTimeEditor);
        startTimeInput.setValue(startTime);

        JLabel endTimeLabel = new JLabel("End time");
        endTimeInput = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeInput, "HH:mm");
        endTimeInput.setEditor(endTimeEditor);
        startTimeInput.setValue(endTime);

        JLabel employeeSelectLabel = new JLabel("Employee");
        employeeSelect = new JComboBox<>(haseGmbHManagement.getAllEmployees().toArray(new Employee[0]));
        employeeSelect.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Employee employee = ((Employee) value);
                String displayValue = "Employee: " + employee.getFirstname() + " " + employee.getLastname();

                return super.getListCellRendererComponent(list, displayValue, index, isSelected, cellHasFocus);
            }
        });

        if (selectedEmployee != null) employeeSelect.setSelectedItem(selectedEmployee);


        JLabel descriptionLabel = new JLabel("Description");
        descriptionInput = new JTextField();
        descriptionInput.setText(description);

        inputPanel.add(dateLabel);
        inputPanel.add(datePicker);
        inputPanel.add(startTimeLabel);
        inputPanel.add(startTimeInput);
        inputPanel.add(endTimeLabel);
        inputPanel.add(endTimeInput);
        inputPanel.add(employeeSelectLabel);
        inputPanel.add(employeeSelect);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionInput);

        return inputPanel;
    }

    private JPanel createButtonsPanel(HaseGmbHManagement haseGmbHManagement, ActivityRecord activityRecord, Contract contract) {
        JPanel buttonsPanel = new JPanel();

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200, 30));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrUpdateActivityRecordFromFormData(activityRecord, haseGmbHManagement, contract);
            }
        });

        buttonsPanel.add(saveButton);

        return buttonsPanel;
    }

    private void addPaddingToMainWindow() {
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setBorder(padding);
    }

    private void createOrUpdateActivityRecordFromFormData(ActivityRecord activityRecord, HaseGmbHManagement haseGmbHManagement, Contract contract) {
        LocalDate date = DateUtils.asLocalDate(datePicker.getDate());
        LocalTime startTime = DateUtils.asLocalTime((Date) endTimeInput.getValue());
        LocalTime endTime = DateUtils.asLocalTime((Date) startTimeInput.getValue());
        Employee selectedEmployee = (Employee) employeeSelect.getSelectedItem();
        String description = descriptionInput.getText();

        if (activityRecord == null) {
            activityRecord = new ActivityRecord(
                    date, startTime, endTime, selectedEmployee, description
            );

            haseGmbHManagement.addNewWorkingRecord(contract.getContractID(), activityRecord);
        } else {
            activityRecord.setDate(date);
            activityRecord.setStartTime(startTime);
            activityRecord.setEndTime(endTime);
            activityRecord.setDescription(description);
            activityRecord.setEmployee(selectedEmployee);
        }
    }
}
