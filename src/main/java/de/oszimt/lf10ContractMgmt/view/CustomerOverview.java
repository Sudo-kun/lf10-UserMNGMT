package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.Customer;
import de.oszimt.lf10ContractMgmt.model.Employee;
import de.oszimt.lf10ContractMgmt.util.FontUtil;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class CustomerOverview extends AbstractOverview {
  private static final JButton outerSearchButton = new JButton("Search");

  private ArrayList<Customer> customers;
  private ArrayList<Customer> filteredCustomers;

  public CustomerOverview(ArrayList<Customer> customers) {
    this.actionButtonsColumn = 5;
    this.idColumn = 0;

    resetSearchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetSearch();
      }
    });

    setCustomers(customers);
    drawOverview();
    setVisible(true);
  }

  private void resetSearch() {
    setCustomers(customers);
    updateTable();
    resetSearchButton.setVisible(false);
  }

  private void setCustomers(ArrayList<Customer> customers) {
    this.customers = customers;
    this.filteredCustomers = customers;
  }

  @Override
  protected DefaultTableModel createModel() {
    model = new DefaultTableModel();
    model.addColumn("firstname");
    model.addColumn("lastname");
    model.addColumn("birthday");
    model.addColumn("email");
    model.addColumn("address");
    model.addColumn("");

    for (Customer customer : filteredCustomers) {
      model.addRow(
        new Object[]{
          customer.getFirstname(),
          customer.getLastname(),
          customer.getBirthday(),
          customer.getEmail(),
          customer.getAddress().toString(),
          "Actions"
        }
      );
    }

    return model;

  }

  @Override
  public int getIdByRow(int row) {
    return filteredCustomers.get(row).getCustomerID();
  }

  @Override
  protected void createSearchWindow() {
    JFrame searchWindow = new JFrame("Suchen");
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

    JLabel emailLabel = new JLabel("E-Mail");
    JTextField emailField = new JTextField("E-Mail");
    emailField.setForeground(Color.GRAY);

    JLabel birthdayLabel = new JLabel("Geburtsdatum");
    JXDatePicker birthdayField = new JXDatePicker(new Date());

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
        if (emailField.getText().equals("E-Mail")) {
          emailField.setText("");
          emailField.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (emailField.getText().isEmpty()) {
          emailField.setText("E-Mail");
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
    searchPanel.add(birthdayLabel);
    searchPanel.add(birthdayField);

    JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    searchButtonPanel.add(outerSearchButton);

    JPanel searchBoxPanel = new JPanel();
    searchBoxPanel.setLayout(new BoxLayout(searchBoxPanel, BoxLayout.Y_AXIS));
    searchBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    searchBoxPanel.add(Box.createVerticalGlue());
    searchBoxPanel.add(createHeadline("Suchen"));
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
      Date birthdate = birthdayField.getDate();

      filterList(new CustomerSearchCriteria(firstname, lastname, address, email, birthdate));

      innerSearchButton.setEnabled(true);
      searchWindow.dispose();
    });
  }

  private void updateTable() {
    model.setRowCount(0);

    for (Customer customer : filteredCustomers) {
      model.addRow(
        new Object[]{
          customer.getFirstname(),
          customer.getLastname(),
          customer.getBirthday(),
          customer.getEmail(),
          customer.getAddress().toString(),
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

  private void filterList(CustomerSearchCriteria searchCriteria) {
    filteredCustomers = new ArrayList<>();

    for (Customer customer : customers) {
      if (customer.getFirstname().contains(searchCriteria.getFirstname())) {
        filteredCustomers.add(customer);
      } else if (customer.getLastname().contains(searchCriteria.getLastname())) {
        filteredCustomers.add(customer);
      } else if (customer.getAddress().toString().contains(searchCriteria.getAddress())) {
        filteredCustomers.add(customer);
      } else if (customer.getEmail().contains(searchCriteria.getEmail())) {
        filteredCustomers.add(customer);
      } else if (customer.getBirthday().isEqual(searchCriteria.getBirthdate())) {
        filteredCustomers.add(customer);
      }
    }

    if (filteredCustomers.isEmpty()) {
      JOptionPane.showMessageDialog(null, "No contracts found!");
    }

    if (filteredCustomers.size() != customers.size()) {
      resetSearchButton.setVisible(true);
    }

    updateTable();
  }
}
