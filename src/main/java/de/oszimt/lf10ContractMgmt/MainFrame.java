package de.oszimt.lf10ContractMgmt;

import de.oszimt.lf10ContractMgmt.impl.HaseGmbHManagement;
import de.oszimt.lf10ContractMgmt.view.ActivityDetailsView;
import de.oszimt.lf10ContractMgmt.mock_data.MockDataCreator;
import de.oszimt.lf10ContractMgmt.view.ActivityOverview;
import de.oszimt.lf10ContractMgmt.view.LoginPanel;
import de.oszimt.lf10ContractMgmt.view.TaskDetailsView;

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

    TaskDetailsView taskDetailsView;

    HaseGmbHManagement haseGmbHManagement = new HaseGmbHManagement();

    public MainFrame() {
        setupDetailsView();
        setupTestTaskView();

        setVisible(true);
        // setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);

        // Hier können Sie Ihre Komponenten hinzufügen
        // z.B. ein Login-Panel, eine Menüleiste, etc.
        //add(loginPanel);
        //add(taskDetailsView);
        //add(activityDetailsView);

        loginPanel.setLoginActionListener(e -> {
            // Hier können Sie den Code zum Überprüfen der Anmeldedaten einfügen
            // z.B. Verbindung zur Datenbank, um die Anmeldedaten zu überprüfen
            char[] password = loginPanel.getPasswordValue();
            String username = loginPanel.getUsernameValue();

            final var validData = username.equals(LOGIN_USERNAME) || new String(password).equals(LOGIN_PASSWORD);

            loginPanel.setError(!validData);

            if (validData) {
                loginPanel.setVisible(false);

                showActivityOverview();
            }
        });

    }

    public void showActivityOverview() {
        // go to next view
        setSize(1920, 1080);
        setVisible(true);

        MockDataCreator mockDataCreator = new MockDataCreator();

        ActivityOverview activityOverview = new ActivityOverview(this);

        activityOverview.drawActivityOverview(mockDataCreator.createContractMockData((int) (Math.random() * 20) + 1));

    }

    private void setupDetailsView() {
        activityDetailsView = new ActivityDetailsView(haseGmbHManagement.getContract(21001101));
    }

    private void setupTestTaskView() {
        taskDetailsView = new TaskDetailsView(haseGmbHManagement.getContract(21001101).getActivityRecordList().get(0), haseGmbHManagement, null);
    }
}


