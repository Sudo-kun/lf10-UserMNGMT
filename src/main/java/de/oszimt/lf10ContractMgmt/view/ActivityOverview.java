package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.Contract;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ActivityOverview {
    JFrame window;

    public ActivityOverview(JFrame window) {
        this.window = window;
    }

    public void drawActivityOverview(Contract[] contracts) {
        window.setTitle("Activity Overview");
        JPanel activityOverviewPanel = new JPanel();
        activityOverviewPanel.setLayout(new BoxLayout(activityOverviewPanel, BoxLayout.Y_AXIS));

        JPanel headlinePanel = createHeadline();
        activityOverviewPanel.add(headlinePanel);
        activityOverviewPanel.add(Box.createVerticalGlue());

        JPanel tablePanel = createTable(contracts);
        activityOverviewPanel.add(tablePanel);

        window.add(activityOverviewPanel);
        window.revalidate();
        window.repaint();
    }

    private static JPanel createTable(Contract[] contracts) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Customer");
        model.addColumn("Employee");
        model.addColumn("Type");
        model.addColumn("State");
        model.addColumn("Actions");

        //add data to table from the contract array
        for (Contract contract : contracts) {
            model.addRow(new Object[]{contract.getContractID(), contract.getCustomer().getLastname(), contract.getProjectOwner().getLastname(), contract.getContractType(), contract.getState(), "Actions"});
        }

        //create table
        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);

        // create a search field for customer and employee
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setMaximumSize(new Dimension(200, 30));
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchField.setAlignmentY(Component.TOP_ALIGNMENT);

        // create a search button
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setMaximumSize(new Dimension(100, 30));
        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchButton.setAlignmentY(Component.TOP_ALIGNMENT);

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.setMaximumSize(new Dimension(100, 30));
        addButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        addButton.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setSize(1800, 40);
        searchPanel.setPreferredSize(new Dimension(1800, 40));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(Box.createHorizontalGlue());
        searchPanel.add(addButton);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        // create search functionality for table
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        searchButton.addActionListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        //set editor for action buttons column
        int actionButtonsColumn = 5;
        int idColumn = 0;
        table.getColumnModel().getColumn(actionButtonsColumn).setCellRenderer(new ButtonRenderer(idColumn));
        table.getColumnModel().getColumn(actionButtonsColumn).setCellEditor(new ButtonEditor(idColumn));

        table.setRowHeight(40);

        //create and show table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
        scrollPane.setSize(1800, 500);
        scrollPane.setPreferredSize(new Dimension(1800, 500));

        // table should be inside a new panel with a FlowLayout
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.add(searchPanel);
        tablePanel.add(scrollPane);

        return tablePanel;
    }
    

    private static JPanel createHeadline() {
        JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new BoxLayout(headlinePanel, BoxLayout.X_AXIS));

        JLabel headLine = new JLabel("Activity Overview");
        headLine.setAlignmentY(Component.TOP_ALIGNMENT);
        headLine.setFont(new Font("Serif", Font.BOLD, 25));

        headlinePanel.add(Box.createHorizontalGlue());
        headlinePanel.add(headLine);
        headlinePanel.add(Box.createRigidArea(new Dimension(0,20)));
        headlinePanel.add(Box.createHorizontalGlue());

        return headlinePanel;
    }
}




