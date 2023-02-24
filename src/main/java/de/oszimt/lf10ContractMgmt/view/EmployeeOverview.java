package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.Employee;
import de.oszimt.lf10ContractMgmt.util.FontUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeeOverview extends AbstractOverview {
  private static final JButton outerSearchButton = new JButton("Search");
  ArrayList<Employee> employees;
  private ArrayList<Employee> filteredEmployees;

  public EmployeeOverview(ArrayList<Employee> employees) {
    this.actionButtonsColumn = 5;
    this.idColumn = 0;

    resetSearchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetSearch();
      }
    });

    setEmployees(employees);
    drawOverview();
    setVisible(true);
  }

  private void resetSearch() {
    setEmployees(employees);
    updateTable();
    resetSearchButton.setVisible(false);
  }

  private void setEmployees(ArrayList<Employee> employees) {
    this.employees = employees;
    this.filteredEmployees = employees;
  }

  @Override
  protected DefaultTableModel createModel() {
    model = new DefaultTableModel();
    model.addColumn("firstname");
    model.addColumn("lastname");
    model.addColumn("address");
    model.addColumn("email");
    model.addColumn("telephone");
    model.addColumn("Actions");

    for (Employee employee : filteredEmployees) {
      model.addRow(
        new Object[]{
          employee.getFirstname(),
          employee.getLastname(),
          employee.getAddress().toString(),
          employee.getEmail(),
          employee.getTelephone(),
          "Actions"
        }
      );
    }

    return model;
  }

  @Override
  public int getIdByRow(int row) {
    return filteredEmployees.get(row).getEmployeeID();
  }

  protected void createSearchWindow() {
    JFrame searchWindow = new JFrame("Search");
    searchWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    searchWindow.setSize(500, 500);
    searchWindow.setLocationRelativeTo(null);
    searchWindow.setVisible(true);

    JPanel searchPanel = new JPanel(new GridLayout(6, 2, 10, 10));

    JLabel firstnameLabel = new JLabel("Vorname");
    JTextField firstnameField = new JTextField("Vorname");
    firstnameField.setForeground(Color.GRAY);

    JLabel lastnameLabel = new JLabel("Nachname");
    JTextField lastnameField = new JTextField("Nachname");
    lastnameField.setForeground(Color.GRAY);

    JLabel addressLabel = new JLabel("Adresse");
    JTextField addressField = new JTextField("Adresse");
    addressField.setForeground(Color.GRAY);

    JLabel emailLabel = new JLabel("Email");
    JTextField emailField = new JTextField("Email");
    emailField.setForeground(Color.GRAY);

    JLabel telefonLabel = new JLabel("Telefon");
    JTextField telefonField = new JTextField("Telefon");
    telefonField.setForeground(Color.GRAY);

    firstnameField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (firstnameField.getText().equals("Vorname")) {
          firstnameField.setText("");
          firstnameField.setForeground(Color.BLACK);

        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (firstnameField.getText().isEmpty()) {
          firstnameField.setText("Vorname");
          firstnameField.setForeground(Color.GRAY);
        }
      }
    });
    lastnameField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (lastnameField.getText().equals("Nachname")) {
          lastnameField.setText("");
          lastnameField.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (lastnameField.getText().isEmpty()) {
          lastnameField.setText("Nachname");
          lastnameField.setForeground(Color.GRAY);
        }
      }
    });
    addressField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (addressField.getText().equals("Adresse")) {
          addressField.setText("");
          addressField.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (addressField.getText().isEmpty()) {
          addressField.setText("Adresse");
          addressField.setForeground(Color.GRAY);
        }
      }
    });
    emailField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (emailField.getText().equals("Eamil")) {
          emailField.setText("");
          emailField.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (emailField.getText().isEmpty()) {
          emailField.setText("Eamil");
          emailField.setForeground(Color.GRAY);
        }
      }
    });

    searchPanel.add(firstnameLabel);
    searchPanel.add(firstnameField);
    searchPanel.add(lastnameLabel);
    searchPanel.add(lastnameField);
    searchPanel.add(addressLabel);
    searchPanel.add(addressField);
    searchPanel.add(emailLabel);
    searchPanel.add(emailField);
    searchPanel.add(telefonLabel);
    searchPanel.add(telefonField);

    JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    searchButtonPanel.add(outerSearchButton);

    JPanel searchBoxPanel = new JPanel();
    searchBoxPanel.setLayout(new BoxLayout(searchBoxPanel, BoxLayout.Y_AXIS));
    searchBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    searchBoxPanel.add(Box.createVerticalGlue());
    searchBoxPanel.add(createHeadline("Search"));
    searchBoxPanel.add(Box.createVerticalGlue());
    searchBoxPanel.add(searchPanel);
    searchBoxPanel.add(Box.createVerticalGlue());
    searchBoxPanel.add(searchButtonPanel);

    searchWindow.setPreferredSize(searchBoxPanel.getSize());
    searchWindow.add(searchBoxPanel);
    searchWindow.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        innerSearchButton.setEnabled(true);
      }
    });

    outerSearchButton.addActionListener(e -> {
      String firstname = firstnameField.getText();
      String lastname = lastnameField.getText();
      String address = addressField.getText();
      String email = emailField.getText();
      String telefon = telefonField.getText();

      filterList(new EmployeeSearchCriteria(firstname, lastname, address, email, telefon));

      innerSearchButton.setEnabled(true);
      searchWindow.dispose();
    });
  }

  private void updateTable() {
    model.setRowCount(0);

    for (Employee employee : filteredEmployees) {
      model.addRow(
        new Object[]{
          employee.getFirstname(),
          employee.getLastname(),
          employee.getAddress().toString(),
          employee.getEmail(),
          employee.getTelephone(),
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
    headLine.setFont(FontUtil.getBoldFont(25));

    headlinePanel.add(Box.createHorizontalGlue());
    headlinePanel.add(headLine);
    headlinePanel.add(Box.createHorizontalGlue());

    return headlinePanel;
  }

  private void filterList(EmployeeSearchCriteria searchCriteria) {
    filteredEmployees = new ArrayList<>();

    for (Employee employee : employees) {
      if (employee.getFirstname().contains(searchCriteria.getFirstname())) {
        filteredEmployees.add(employee);
      } else if (employee.getLastname().contains(searchCriteria.getLastname())) {
        filteredEmployees.add(employee);
      } else if (employee.getAddress().toString().contains(searchCriteria.getAddress())) {
        filteredEmployees.add(employee);
      } else if (employee.getEmail().contains(searchCriteria.getEmail())) {
        filteredEmployees.add(employee);
      } else if (employee.getTelephone().contains(searchCriteria.getTelefon())) {
        filteredEmployees.add(employee);
      }
    }

    if (filteredEmployees.isEmpty()) {
      JOptionPane.showMessageDialog(null, "No contracts found!");
    }

    if (filteredEmployees.size() != employees.size()) {
      resetSearchButton.setVisible(true);
    }

    updateTable();
  }
}
