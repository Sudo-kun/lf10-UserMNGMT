package de.oszimt.lf10ContractMgmt;

import de.oszimt.lf10ContractMgmt.view.LoginPanel;

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

    public MainFrame() {
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);

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

            if (validData) loginPanel.setVisible(false);
        });

    }
}

