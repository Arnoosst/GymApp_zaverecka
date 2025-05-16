package GUI;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginPanel(UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new GridLayout(3, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JTextField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            User user = userManager.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                parentPanel.add(new MainMenuPanel(user, userManager, cardLayout, parentPanel), "mainMenu");
                cardLayout.show(parentPanel, "mainMenu");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "register");
        });
    }
}
