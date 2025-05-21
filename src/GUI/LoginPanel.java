package GUI;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;


/**
 * Panel that provides the login interface for users.
 * Allows users to enter their credentials and log into the system,
 * or navigate to the registration panel.
 *
 * @Author: Vojtěch Malínek
 */
public class LoginPanel extends JPanel {
    private final JTextField usernameField;
    private final JTextField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    private final AppFrame appFrame;


    /**
     * Creates a new LoginPanel.
     *
     * @param userManager the manager responsible for handling user login
     * @param cardLayout the layout manager used to switch between panels
     * @param parentPanel the container that holds the panels
     * @param appFrame the main application frame used to initialize user panels upon login
     */
    public LoginPanel(UserManager userManager, CardLayout cardLayout, JPanel parentPanel, AppFrame appFrame) {
        this.appFrame = appFrame;

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
                appFrame.initializeUserPanels(user);
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
