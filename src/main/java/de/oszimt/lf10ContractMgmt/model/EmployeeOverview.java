package de.oszimt.lf10ContractMgmt.model;

import de.oszimt.lf10ContractMgmt.view.AbstractOverview;
import de.oszimt.lf10ContractMgmt.view.SearchCriteria;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeOverview extends AbstractOverview {
  private static final JButton outerSearchButton = new JButton("Search");
  ArrayList<Employee> employees;
  private ArrayList<Employee> filteredEmployees;

  public EmployeeOverview(ArrayList<Employee> employees) {
    this.actionButtonsColumn = 6;
    this.idColumn = 0;

    setEmployees(employees);
    drawOverview();
    setVisible(true);
  }

  private void setEmployees(ArrayList<Employee> employees) {
    this.employees = employees;
    this.filteredEmployees = employees;
  }

  @Override
  protected DefaultTableModel createModel() {
    model = new DefaultTableModel();
    model.addColumn("employeeID");
    model.addColumn("firstname");
    model.addColumn("lastname");
    model.addColumn("address");
    model.addColumn("email");
    model.addColumn("telephone");
    model.addColumn("Actions");

    for (Employee employee : filteredEmployees) {
      model.addRow(
        new Object[]{
          employee.getEmployeeID(),
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
      String firstname = firstnameField.getText();
      String lastname = lastnameField.getText();
      String address = addressField.getText();
      String email = emailField.getText();
      String telefon = telefonField.getText();

      // create a list with the search criteria
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
          employee.getEmployeeID(),
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
    headLine.setFont(new Font("Serif", Font.BOLD, 25));

    headlinePanel.add(Box.createHorizontalGlue());
    headlinePanel.add(headLine);
    headlinePanel.add(Box.createHorizontalGlue());

    return headlinePanel;
  }

  private void filterList(EmployeeSearchCriteria searchCriteria) {
    filteredEmployees = new ArrayList<>();

    for (Employee employee : employees) {
      if (employee.getFirstname().equals(searchCriteria.getFirstname())) {
        System.out.println("Firstname matches");
        filteredEmployees.add(employee);
        System.out.println("Added employee " + employee.getFirstname() + " to filtered list");
      } else if (employee.getLastname().equals(searchCriteria.getLastname())) {
        System.out.println("Lastname matches");
        filteredEmployees.add(employee);
        System.out.println("Added employee " + employee.getLastname() + " to filtered list");
      } else if (employee.getAddress().toString().contains(searchCriteria.getAddress())) {
        System.out.println("Address matches");
        filteredEmployees.add(employee);
        System.out.println("Added employee " + employee.getAddress() + " to filtered list");
      } else if (employee.getEmail().equals(searchCriteria.getEmail())) {
        System.out.println("email matches");
        filteredEmployees.add(employee);
        System.out.println("Added employee " + employee.getEmail() + " to filtered list");
      } else if (employee.getTelephone().contains(searchCriteria.getTelefon())) {
        System.out.println("Telefon matches");
        filteredEmployees.add(employee);
        System.out.println("Added employee " + employee.getTelephone() + " to filtered list");
      } else {
        System.out.println("No match");
      }
    }

    updateTable();
  }
}
