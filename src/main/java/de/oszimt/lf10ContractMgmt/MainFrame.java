package de.oszimt.lf10ContractMgmt;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.view.*;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    public static final String LOGIN_USERNAME = "admin";
    public static final String LOGIN_PASSWORD = "password";

    LoginPanel loginPanel = new LoginPanel();
    ActivityDetailsView activityDetailsView;

    HaseGmbHManagement haseGmbHManagement = new HaseGmbHManagement();

    MainLayout mainLayout;

    public MainFrame() {
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(false);
        setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 400);

        // Hier können Sie Ihre Komponenten hinzufügen
        // z.B. ein Login-Panel, eine Menüleiste, etc.
        add(loginPanel);

        loginPanel.setLoginActionListener(e -> {
            // Hier können Sie den Code zum Überprüfen der Anmeldedaten einfügen
            // z.B. Verbindung zur Datenbank, um die Anmeldedaten zu überprüfen
            char[] password = loginPanel.getPasswordValue();
            String username = loginPanel.getUsernameValue();

            final var validData = username.equals(LOGIN_USERNAME) || new String(password).equals(LOGIN_PASSWORD);

            loginPanel.setError(!validData);

            if (validData) {
                loginPanel.setVisible(false);
                setResizable(true);
                setAlwaysOnTop(false);


                showMainLayout();
                setSize(1920, 1080);
                showActivityOverview();
            }
        });

    }

    public void showMainLayout () {
        setSize(1920, 1080);
        mainLayout = new MainLayout();
        mainLayout.setHeadline("Test");
        add(mainLayout);

        mainLayout.setContractOverviewAction(e -> showActivityOverview());
        mainLayout.setEmployeeOverviewAction(e -> showEmployeeOverview());
    }

    public void showEmployeeOverview() {
        EmployeeOverview employeesOverview = new EmployeeOverview(
                haseGmbHManagement.getAllEmployees());

        employeesOverview.setEditActionListener(e -> {
            int row = Integer.parseInt(e.getActionCommand());
            System.out.println("Edit button clicked on row: " + row);

            employeesOverview.setVisible(false);
            setupDetailsView(employeesOverview.getIdByRow(row));

            System.out.println("EmployeeID: " + employeesOverview.getIdByRow(row));

            mainLayout.setHeadline("Mitarbeiterdetails");
            mainLayout.setBody(employeesOverview);
            employeesOverview.setVisible(true);
        });

        employeesOverview.setDeleteActionListener(e -> {
            int row = Integer.parseInt(e.getActionCommand());
            System.out.println("Delete button clicked on row: " + row);

            haseGmbHManagement.deleteEmployee(employeesOverview.getIdByRow(row));
            employeesOverview.removeRow(row);
        });

        mainLayout.setHeadline("Mitarbeiterübersicht");
        mainLayout.setBody(employeesOverview);
    }

    public void showActivityOverview() {
        ActivityOverview activityOverview = new ActivityOverview(
                haseGmbHManagement.getAllContracts());

        activityOverview.setEditActionListener(e -> {
            int row = Integer.parseInt(e.getActionCommand());
            System.out.println("Edit button clicked on row: " + row);

            activityOverview.setVisible(false);
            setupDetailsView(activityOverview.getIdByRow(row));

            System.out.println("ContractID: " + activityOverview.getIdByRow(row));

            mainLayout.setHeadline("Aktivitätsdetails");
            mainLayout.setBody(activityDetailsView);
            activityDetailsView.setVisible(true);
        });

        activityOverview.setDeleteActionListener(e -> {
            int row = Integer.parseInt(e.getActionCommand());
            System.out.println("Delete button clicked on row: " + row);

            haseGmbHManagement.deleteContract(activityOverview.getIdByRow(row));
            activityOverview.removeRow(row);
        });

        mainLayout.setHeadline("Aktivitätenübersicht");
        mainLayout.setBody(activityOverview);
    }

    private void setupDetailsView(int ContractID) {
        activityDetailsView = new ActivityDetailsView(haseGmbHManagement.getContract(ContractID), haseGmbHManagement);
    }
}


