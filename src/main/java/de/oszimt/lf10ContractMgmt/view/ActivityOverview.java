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
  ArrayList<Contract> filteredContracts;

  private ButtonEditor buttonEditor;

  private JTable table;

  private DefaultTableModel model;

  private final JButton addButton = new JButton("Add");

  static JButton outerSearchButton = new JButton("Search");

  static JButton innerSearchButton = new JButton("Search");


  public ActivityOverview(ArrayList<Contract> contracts) {
    setupWindow();
    setContracts(contracts);
    drawOverview("Activity Overview");
    setVisible(true);
  }

  private void setupWindow() {
    setSize(1920, 1080);
  }

  private void setContracts(ArrayList<Contract> contracts) {
    this.contracts = contracts;
    this.filteredContracts = contracts;
  }

  private void filterList(SearchCriteria searchCriteria) {
    filteredContracts = new ArrayList<>();

    for (Contract contract : contracts) {
      if (contract.getCustomer().getLastname().equals(searchCriteria.getCustomer())) {
        System.out.println("Customer matches");
        filteredContracts.add(contract);
        System.out.println("Added contract " + contract.getContractID() + " to filtered list");
      } else if (contract.getProjectOwner().getLastname().equals(searchCriteria.getProjectOwner())) {
        System.out.println("Project owner matches");
        filteredContracts.add(contract);
        System.out.println("Added contract " + contract.getContractID() + " to filtered list");
      } else if (contract.getContractType().equals(searchCriteria.getContractType())) {
        System.out.println("Contract type matches");
        filteredContracts.add(contract);
        System.out.println("Added contract " + contract.getContractID() + " to filtered list");
      } else if (contract.getState().equals(searchCriteria.getState())) {
        System.out.println("State matches");
        filteredContracts.add(contract);
        System.out.println("Added contract " + contract.getContractID() + " to filtered list");
      } else if (contract.getCreationDate().isBefore(searchCriteria.getEndDate())
        && contract.getCreationDate().isAfter(searchCriteria.getStartDate())) {
        System.out.println("Creation date matches");
        filteredContracts.add(contract);
        System.out.println("Added contract " + contract.getContractID() + " to filtered list");
      } else {
        System.out.println("No match");
      }
    }

    updateTable();
  }

  public void setEditActionListener(ActionListener actionListener) {
    buttonEditor.setEditActionListener(actionListener);
  }

  public void setNewActionListener(ActionListener actionListener) {
    addButton.addActionListener(actionListener);
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

    for (Contract contract : filteredContracts) {
      model.addRow(
        new Object[]{
          contract.getContractID(),
          contract.getCustomer().getLastname(),
          contract.getProjectOwner().getLastname(),
          contract.getContractType(),
          contract.getState(),
          "Actions"
        }
      );
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
    table.setRowHeight(35);

    // create a search button
    innerSearchButton.setPreferredSize(new Dimension(100, 30));
    innerSearchButton.setMaximumSize(new Dimension(100, 30));
    innerSearchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    innerSearchButton.setAlignmentY(Component.TOP_ALIGNMENT);

    addButton.setPreferredSize(new Dimension(100, 30));
    addButton.setMaximumSize(new Dimension(100, 30));
    addButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
    addButton.setAlignmentY(Component.TOP_ALIGNMENT);

    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
    searchPanel.setSize((int) (screenSize.getWidth() * 0.95), 40);
    searchPanel.setPreferredSize(new Dimension((int) (screenSize.getWidth() * 0.95), 40));
    searchPanel.add(innerSearchButton);
    searchPanel.add(Box.createHorizontalGlue());
    searchPanel.add(addButton);
    searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    searchPanel.setAlignmentY(Component.TOP_ALIGNMENT);

    // create search functionality for table
    TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(rowSorter);

    innerSearchButton.addActionListener(e -> {
      innerSearchButton.setEnabled(false);

      createSearchWindow();
    });

    //set editor for action buttons column
    setTableValues();

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
    setResizeListener(tablePanel, scrollPane, searchPanel);

    return tablePanel;
  }

  private void setResizeListener(JPanel tablePanel, JScrollPane scrollPane, JPanel searchPanel) {
    tablePanel.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        System.out.println("Table resized");
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

  private void createSearchWindow() {
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

    searchButtonPanel.add(outerSearchButton);

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

    outerSearchButton.addActionListener(e -> {
      String customer = customerField.getText();
      String projectOwner = projectOwnerField.getText();
      String contractType = contractTypeField.getText();
      String state = stateField.getText();
      Date startDate = startDateField.getDate();
      Date endDate = endDateField.getDate();

      // create a list with the search criteria
      filterList(new SearchCriteria(customer, projectOwner, contractType, state, startDate, endDate));

      innerSearchButton.setEnabled(true);
      searchWindow.dispose();
    });
  }

  public int getIdByRow(int row) {
    return contracts.get(row).getContractID();
  }

  public void setTableValues() {

    int actionButtonsColumn = 5;
    int idColumn = 0;

    buttonEditor = new ButtonEditor(idColumn);

    table.getColumnModel().getColumn(actionButtonsColumn).setCellRenderer(new ButtonRenderer(idColumn));
    table.getColumnModel().getColumn(actionButtonsColumn).setCellEditor(buttonEditor);
  }

  public void removeRow(int row) {
    model.removeRow(row);
  }

  private void updateTable() {
    model.setRowCount(0);

    for (Contract contract : filteredContracts) {
      model.addRow(
        new Object[]
          {
            contract.getContractID(),
            contract.getCustomer().getLastname(),
            contract.getProjectOwner().getLastname(),
            contract.getContractType(),
            contract.getState(),
            "Actions"
          }
      );
    }
  }
}




