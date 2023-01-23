package de.oszimt.lf10ContractMgmt.view;

import javax.swing.*;
import java.awt.*;

/**
 * created by Brisko Bernburg
 */
public class Login extends JFrame {

    public static final String VIEW_TITLE = "Login";

    private JButton loginBtn;

    private JTextField usernameField;
    private JTextField passwordField;

    public static void main(String[] args) {
        var frame = new Login();
        frame.setVisible(true);
    }

    public Login() {
        super(VIEW_TITLE);

        setAlwaysOnTop(true);
        setSize(300, 275);
        setLayout(new BorderLayout(10, 10));

        var panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        var titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(getTitleLabel(VIEW_TITLE));
        titlePanel.add(Box.createHorizontalGlue());
        panel.add(titlePanel);

        var btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());

        loginBtn = setupLoginButton();
        usernameField = new JTextField(10);
        passwordField = new JTextField(10);

        var usernamePanel = getTextField("Benutzername:", usernameField);
        var passwordPanel = getTextField("Passwort:", passwordField);

        panel.add(usernamePanel);
        panel.add(passwordPanel);

        var buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(loginBtn);
        buttonRow.add(Box.createHorizontalGlue());

        //loginBtn.addActionListener(e -> clickLogin(usernameField.getText(), passwordField.getText()));

        btnPanel.add(buttonRow, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void clickLogin(String username, String password) {
        System.out.println("Benutzername: " + username);
        System.out.println("Passwort: " + password);
    }

    private JButton setupLoginButton() {
        loginBtn = new JButton("Login");
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 32, 10, 32));
        return loginBtn;
    }

    private JLabel getTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        return label;
    }

    private JPanel getTextField(String title, JTextField field) {
        var panel = new JPanel(new GridLayout(2, 1));

        var text = new JLabel(title);
        text.setLabelFor(field);

        field.setMargin(new Insets(0, 8, 0, 8));

        var size = new Dimension(200, 20);
        field.setMaximumSize(size);
        field.setMinimumSize(size);
        field.setSize(size);
        field.setPreferredSize(size);

        panel.add(text);
        panel.add(field);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        return panel;
    }

}
