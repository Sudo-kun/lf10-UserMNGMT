package de.oszimt.lf10ContractMgmt.model;

import de.oszimt.lf10ContractMgmt.view.AbstractOverview;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class EmployeeOverview extends AbstractOverview {
  ArrayList<Employee> employees;
  private ArrayList<Employee> filteredEmployees;

  public EmployeeOverview(ArrayList<Employee> employees) {
    this.actionButtonsColumn = 6;
    this.idColumn = 0;

    setEmployees(employees);
    drawOverview();
    setVisible(true);
  }

  @Override
  protected void createSearchWindow() {

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
}
