package de.oszimt.lf10ContractMgmt.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * created by Brisko Bernburg
 */
public class Login extends JFrame {

    public static final String LOGIN_USERNAME = "admin";
    public static final String LOGIN_PASSWORD = "password";

    public static final String VIEW_TITLE = "Login";

    private JButton loginBtn;
    private JLabel errorLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        var frame = new Login();
        frame.setVisible(true);
    }

    public Login() {
        super(VIEW_TITLE);

        setResizable(false);
        setAlwaysOnTop(true);
        setSize(300, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        var panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        var titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("<html><h1 style='margin: 0;'>" + VIEW_TITLE + "</h1></html>");

        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);

        panel.add(titlePanel);

        var errorPanel = new JPanel(new BorderLayout());

        errorLabel = new JLabel("<html><p style='color: red; margin: 0;'>Anmeldedaten sind falsch</p></html>");

        errorLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        errorLabel.setVisible(false);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorPanel.add(errorLabel);

        panel.add(errorPanel);

        loginBtn = setupLoginButton();
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        var usernamePanel = getTextField("Benutzername:", usernameField);
        var passwordPanel = getTextField("Passwort:", passwordField);

        panel.add(usernamePanel);
        panel.add(passwordPanel);

        var btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        var buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(loginBtn);
        buttonRow.add(Box.createHorizontalGlue());

        btnPanel.add(buttonRow, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.PAGE_END);
    }

    private JButton setupLoginButton() {
        loginBtn = new JButton("Login");
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 32, 10, 32));
        loginBtn.addActionListener(new LoginListener());
        return loginBtn;
    }

    private JPanel getTextField(String title, JTextField field) {
        var panel = new JPanel(new GridLayout(2, 1));

        var text = new JLabel(title);
        text.setLabelFor(field);

        field.setMargin(new Insets(0, 8, 0, 8));

        var size = new Dimension(200, 30);
        field.setMaximumSize(size);
        field.setMinimumSize(size);
        field.setSize(size);
        field.setPreferredSize(size);

        panel.add(text);
        panel.add(field);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        return panel;
    }

    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Hier können Sie den Code zum Überprüfen der Anmeldedaten einfügen
            // z.B. Verbindung zur Datenbank, um die Anmeldedaten zu überprüfen
            char[] password = passwordField.getPassword();
            String username = usernameField.getText();
            if(username.equals(LOGIN_USERNAME) && new String(password).equals(LOGIN_PASSWORD)){
                //JOptionPane.showMessageDialog(null, "Erfolgreich angemeldet");
                errorLabel.setVisible(false);
            }else{
                //JOptionPane.showMessageDialog(null, "Falsche Anmeldedaten");
                errorLabel.setVisible(true);
            }
        }
    }

}
