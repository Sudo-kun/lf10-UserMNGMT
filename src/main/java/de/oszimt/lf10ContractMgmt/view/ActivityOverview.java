package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.Contract;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityOverview extends AbstractOverview {

    ArrayList<Contract> contracts;

    SearchCriteria searchCriteria = null;
    private ActionListener editActionListener;
    private ActionListener deleteActionListener;
    private ActionListener newActionListener;
    private ButtonEditor buttonEditor;

    private JTable table;

    private DefaultTableModel model;

    public ActivityOverview(ArrayList<Contract> contracts) {
        windowInit();
        setContracts(contracts);
        drawOverview("Activity Overview");
        setVisible(true);
    }

    private void windowInit() {
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setBorder(padding);
        setSize(1920, 1080);
        setPreferredSize(new Dimension(1920, 1080));
    }

    private void setContracts(ArrayList<Contract> contracts) {
        System.out.println("setContracts");
        System.out.println(contracts.size());
        this.contracts = contracts;
    }

    // create a function taht takes a list of criteria and returns a list of contracts that match the criteria
    // ignore the criteria that are null
    private ArrayList<Contract> filterList() {
        ArrayList<Contract> filteredContracts = new ArrayList<>();

        if (this.searchCriteria == null) {
            return contracts;
        }

        for (Contract contract : contracts) {
            if (contract.getCustomer().getLastname().equals(searchCriteria.getCustomer())
                    || contract.getProjectOwner().getLastname().equals(searchCriteria.getProjectOwner())
                    || contract.getContractType().equals(searchCriteria.getContractType())
                    || contract.getState().equals(searchCriteria.getState())
                    || contract.getCreationDate().isAfter(searchCriteria.getEndDate())
                    || contract.getCreationDate().isBefore(searchCriteria.getStartDate())
            ) {
                filteredContracts.add(contract);
            }
        }

        return new ArrayList<>(List.of(new Contract[filteredContracts.size()]));
    }

    public void setEditActionListener(ActionListener actionListener) {
        buttonEditor.setEditActionListener(actionListener);
    }
    public void setNewActionListener(ActionListener actionListener) {
        this.newActionListener = actionListener;
    }
    public void setDeleteActionListener(ActionListener actionListener) {
        buttonEditor.setDeleteActionListener(actionListener);
    }

    protected DefaultTableModel createModel() {
        model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Customer");
        model.addColumn("Employee");
        model.addColumn("Type");
        model.addColumn("State");
        model.addColumn("Actions");

        for (Contract contract : contracts) {
            model.addRow(new Object[]{contract.getContractID(), contract.getCustomer().getLastname(), contract.getProjectOwner().getLastname(), contract.getContractType(), contract.getState(), "Actions"});
        }

        return model;
    }

    @Override
    protected JPanel createTable() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        model = createModel();

        //create table
        table = new JTable(model);
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
            searchButton.setEnabled(false);

            createSearchWindow();
        });

        //set editor for action buttons column
        int actionButtonsColumn = 5;
        int idColumn = 0;

        buttonEditor = new ButtonEditor(idColumn);

        table.getColumnModel().getColumn(actionButtonsColumn).setCellRenderer(new ButtonRenderer(idColumn));
        table.getColumnModel().getColumn(actionButtonsColumn).setCellEditor(buttonEditor);

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

    protected static JPanel createHeadline(String headlineText) {
        JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new BoxLayout(headlinePanel, BoxLayout.X_AXIS));

        JLabel headLine = new JLabel(headlineText);
        headLine.setAlignmentY(Component.TOP_ALIGNMENT);
        headLine.setFont(new Font("Serif", Font.BOLD, 25));

        headlinePanel.add(Box.createHorizontalGlue());
        headlinePanel.add(headLine);
        headlinePanel.add(Box.createHorizontalGlue());

        return headlinePanel;
    }

    private static void createSearchWindow() {
        JFrame searchWindow = new JFrame("Search");
        searchWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchWindow.setSize(500, 500);
        searchWindow.setLocationRelativeTo(null);
        searchWindow.setVisible(true);


        JPanel searchPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel customerLabel = new JLabel("Customer");
        JTextField customerField = new JTextField("Customer");
        customerField.setForeground(Color.GRAY);
        JLabel projectOwnerLabel = new JLabel("Project Owner");
        JTextField projectOwnerField = new JTextField("Employee");
        projectOwnerField.setForeground(Color.GRAY);
        JLabel contractTypeLabel = new JLabel("Contract Type");
        JTextField contractTypeField = new JTextField("Contract Type");
        contractTypeField.setForeground(Color.GRAY);
        JLabel stateLabel = new JLabel("State");
        JTextField stateField = new JTextField("State");
        stateField.setForeground(Color.GRAY);
        JLabel startDateLabel = new JLabel("Start Date");
        JXDatePicker startDateField = new JXDatePicker(new Date());
        JLabel endDateLabel = new JLabel("End Date");
        JXDatePicker endDateField = new JXDatePicker(new Date());

        startDateField.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        endDateField.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        customerField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (customerField.getText().equals("Customer")) {
                    customerField.setText("");
                    customerField.setForeground(Color.BLACK);

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (customerField.getText().isEmpty()) {
                    customerField.setText("Customer");
                    customerField.setForeground(Color.GRAY);
                }
            }
        });
        projectOwnerField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (projectOwnerField.getText().equals("Employee")) {
                    projectOwnerField.setText("");
                    projectOwnerField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (projectOwnerField.getText().isEmpty()) {
                    projectOwnerField.setText("Employee");
                    projectOwnerField.setForeground(Color.GRAY);
                }
            }
        });
        contractTypeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (contractTypeField.getText().equals("Contract Type")) {
                    contractTypeField.setText("");
                    contractTypeField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (contractTypeField.getText().isEmpty()) {
                    contractTypeField.setText("Contract Type");
                    contractTypeField.setForeground(Color.GRAY);
                }
            }
        });
        stateField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (stateField.getText().equals("State")) {
                    stateField.setText("");
                    stateField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (stateField.getText().isEmpty()) {
                    stateField.setText("State");
                    stateField.setForeground(Color.GRAY);
                }
            }
        });


        searchPanel.add(customerLabel);
        searchPanel.add(customerField);
        searchPanel.add(projectOwnerLabel);
        searchPanel.add(projectOwnerField);
        searchPanel.add(contractTypeLabel);
        searchPanel.add(contractTypeField);
        searchPanel.add(stateLabel);
        searchPanel.add(stateField);
        searchPanel.add(startDateLabel);
        searchPanel.add(startDateField);
        searchPanel.add(endDateLabel);
        searchPanel.add(endDateField);


        // add a new panel that combines the search fields and the search button
        // the search button should be aligned to the right with a margin of 10px
        JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton searchButton = new JButton("Search");
        searchButtonPanel.add(searchButton);

        // add a new box panel that combines the search panel and the search button panel
        JPanel searchBoxPanel = new JPanel();
        searchBoxPanel.setLayout(new BoxLayout(searchBoxPanel, BoxLayout.Y_AXIS));
        // add a margin of 10px to the left and right
        searchBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        searchBoxPanel.add(Box.createVerticalGlue());
        searchBoxPanel.add(createHeadline("Search"));
        searchBoxPanel.add(Box.createVerticalGlue());
        searchBoxPanel.add(searchPanel);
        searchBoxPanel.add(Box.createVerticalGlue());
        searchBoxPanel.add(searchButtonPanel);

        searchWindow.setPreferredSize(searchBoxPanel.getSize());

        // draw fields
        searchWindow.add(searchBoxPanel);

        // return a list of projects that match the search criteria
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the search criteria
                String customer = customerField.getText();
                String projectOwner = projectOwnerField.getText();
                String contractType = contractTypeField.getText();
                String state = stateField.getText();
                Date startDate = startDateField.getDate();
                Date endDate = endDateField.getDate();


                // create a list with the search criteria
                SearchCriteria searchCriteria = new SearchCriteria(customer, projectOwner, contractType, state, startDate, endDate);
            }
        });
    }

    public int getIdByRow(int row) {
        System.out.println(contracts.get(row).getContractID());
        return contracts.get(row).getContractID();
    }

    public void removeRow(int row) {
        model = createModel();
        table.setModel(model);

        //set editor for action buttons column
        int actionButtonsColumn = 5;
        int idColumn = 0;


        table.getColumnModel().getColumn(actionButtonsColumn).setCellRenderer(new ButtonRenderer(idColumn));
        table.getColumnModel().getColumn(actionButtonsColumn).setCellEditor(buttonEditor);
    }
}




