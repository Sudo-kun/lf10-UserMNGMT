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
        setupActivityDetailsView(activityRecord, haseGmbHManagement);
        setVisible(true);
    }

    private void setupActivityDetailsView(ActivityRecord activityRecord, HaseGmbHManagement haseGmbHManagement) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Task Details");

        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel inputsPanel = createInputsPanel(activityRecord, haseGmbHManagement);
        add(inputsPanel);

        JPanel buttonsPanel = createButtonsPanel();
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        addPaddingToMainWindow();

        setSize(500, 500);
    }

    private JPanel createInputsPanel(ActivityRecord activityRecord, HaseGmbHManagement haseGmbHManagement) {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2));
        inputPanel.setPreferredSize(new Dimension(700, 330));
        inputPanel.setMaximumSize(new Dimension(700, 330));
        inputPanel.setMinimumSize(new Dimension(700, 330));

        JLabel dateLabel = new JLabel("Date");
        datePicker = new JXDatePicker();
        datePicker.setDate(DateUtils.asDate(activityRecord.getDate()));

        JLabel startTimeLabel = new JLabel("Start time");
        startTimeInput = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeInput, "HH:mm");
        startTimeInput.setEditor(startTimeEditor);

        JLabel endTimeLabel = new JLabel("End time");
        endTimeInput = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeInput, "HH:mm");
        endTimeInput.setEditor(endTimeEditor);

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


        JLabel descriptionLabel = new JLabel("Description");
        descriptionInput = new JTextField(activityRecord.getDescription());

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

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200, 30));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActivityRecord activityRecord = createActivityRecordFromFormData();
            }
        });

        buttonsPanel.add(saveButton);

        return buttonsPanel;
    }

    private void addPaddingToMainWindow() {
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setBorder(padding);
    }

    private ActivityRecord createActivityRecordFromFormData() {
        LocalDate date = DateUtils.asLocalDate(datePicker.getDate());
        LocalTime startTime = DateUtils.asLocalTime((Date) endTimeInput.getValue());
        LocalTime endTime = DateUtils.asLocalTime((Date) startTimeInput.getValue());
        Employee selectedEmployee = (Employee) employeeSelect.getSelectedItem();
        String description = descriptionInput.getText();

        ActivityRecord activityRecord = new ActivityRecord(
                date, startTime, endTime, selectedEmployee, description
        );

        return activityRecord;
    }
}
