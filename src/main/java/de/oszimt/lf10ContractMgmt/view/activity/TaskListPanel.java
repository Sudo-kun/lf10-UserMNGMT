package de.oszimt.lf10ContractMgmt.view.activity;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.model.ActivityRecord;
import de.oszimt.lf10ContractMgmt.model.Contract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskListPanel extends JPanel {
    private ArrayList<ActivityRecord> activityRecordBufferList;

    public TaskListPanel(Contract contract, HaseGmbHManagement haseGmbHManagement) {
        if (contract != null) {
            activityRecordBufferList = (ArrayList<ActivityRecord>) contract.getActivityRecordList().clone();
        } else {
            activityRecordBufferList = new ArrayList<>();
        }

        setupPanelSettings();
        addHeaders();
        addTasksToTaskList(haseGmbHManagement);
    }

    private void addHeaders() {
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

        add(timeLabel);
        add(employeeLabel);
        add(descriptionLabel);
        add(actionsLabel);
    }

    private void addTasksToTaskList(HaseGmbHManagement haseGmbHManagement) {
        for (ActivityRecord activityRecord : activityRecordBufferList) {
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

            add(timeText);
            add(employeeNameText);
            add(descriptionText);
            add(taskActionsButtonPanel);
        }
    }
    private void setupPanelSettings() {
        setPreferredSize(new Dimension(700, 40 + 40 * activityRecordBufferList.size()));
        setMaximumSize(new Dimension(700, 40 + 40 * activityRecordBufferList.size()));
        setMinimumSize(new Dimension(700, 40 + 40 * activityRecordBufferList.size()));
        setLayout(new GridLayout(1 + activityRecordBufferList.size(), 4));
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }


    private void removeTask(ActivityRecord activityRecord) {
        activityRecordBufferList.remove(activityRecord);
    }

    public void reloadTaskList(HaseGmbHManagement haseGmbHManagement) {
        this.removeAll();
        setupPanelSettings();
        addHeaders();
        addTasksToTaskList(haseGmbHManagement);

        this.repaint();
        this.revalidate();
    }

    public ArrayList<ActivityRecord> getActivityRecordBufferList() {
        return activityRecordBufferList;
    }

    public JButton createNewTaskButton(HaseGmbHManagement haseGmbHManagement) {
        JButton addNewTaskButton = new JButton("Add new task");
        addNewTaskButton.addActionListener(e -> {
            new TaskDetailsView(null, haseGmbHManagement, activityRecordBufferList, this);
        });

        return addNewTaskButton;
    }
}
