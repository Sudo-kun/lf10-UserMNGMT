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
  private static final JButton outerSearchButton = new JButton("Search");

  ArrayList<Contract> contracts;

  ArrayList<Contract> filteredContracts;

  public ActivityOverview(ArrayList<Contract> contracts) {
    this.actionButtonsColumn = 5;
    this.idColumn = 0;

    setContracts(contracts);
    drawOverview();
    setVisible(true);
  }

  private void setContracts(ArrayList<Contract> contracts) {
    this.contracts = contracts;
    this.filteredContracts = contracts;
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

  protected void createSearchWindow() {
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
    return filteredContracts.get(row).getContractID();
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
}