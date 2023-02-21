package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.Contract;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        activityOverviewPanel.add(Box.createVerticalGlue());
        activityOverviewPanel.add(headlinePanel);
        activityOverviewPanel.add(Box.createVerticalGlue());

        JPanel tablePanel = createTable(contracts);
        activityOverviewPanel.add(tablePanel);

        window.add(activityOverviewPanel);
        window.revalidate();
        window.repaint();

        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                activityOverviewPanel.setSize(window.getSize());
                window.revalidate();
                window.repaint();
            }
        });
    }

    private static JPanel createTable(Contract[] contracts) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
        searchPanel.setSize((int) (screenSize.getWidth() * 0.95), 40);
        searchPanel.setPreferredSize(new Dimension((int) (screenSize.getWidth() * 0.95), 40));
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
        Dimension scrollPaneSize = new Dimension((int) (screenSize.getWidth() * 0.95), (int) (screenSize.getHeight() * 0.90));
        scrollPane.setSize(scrollPaneSize);
        scrollPane.setPreferredSize(scrollPaneSize);

        // table should be inside a new panel with a FlowLayout
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.add(searchPanel);
        tablePanel.add(scrollPane);

        // handle window resize and resize the table accordingly while keeping the space around the table constant
        tablePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Dimension windowSize = tablePanel.getSize();
                Dimension newScrollPaneSize = new Dimension((int) (windowSize.getWidth() * 0.95), (int) (windowSize.getHeight() * 0.90));
                scrollPane.setSize(newScrollPaneSize);
                scrollPane.setPreferredSize(newScrollPaneSize);
                searchPanel.setSize((int) newScrollPaneSize.getWidth(), 40);
                searchPanel.setPreferredSize(new Dimension((int) (windowSize.getWidth() * 0.95), 40));

                tablePanel.revalidate();
                tablePanel.repaint();
            }
        });


        return tablePanel;
    }

    private static JPanel createHeadline() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new BoxLayout(headlinePanel, BoxLayout.X_AXIS));

        JLabel headLine = new JLabel("Activity Overview");
        headLine.setAlignmentY(Component.TOP_ALIGNMENT);
        headLine.setFont(new Font("Serif", Font.BOLD, 25));

        headlinePanel.add(Box.createHorizontalGlue());
        headlinePanel.add(headLine);
        headlinePanel.add(Box.createHorizontalGlue());

        return headlinePanel;
    }
}




