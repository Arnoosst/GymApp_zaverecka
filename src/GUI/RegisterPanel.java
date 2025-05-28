package GUI;

import Model.UserManager;
import Model.User;
import Model.Gender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel that allows the user to register a new account.
 * Collects login credentials and personal information.
 * On successful registration, switches back to the login panel.
 *
 * Author: Vojtěch Malínek
 */
public class RegisterPanel extends JPanel {
    private JTextField usernameField, passwordField, ageField, heightField, weightField, nameField;
    private JComboBox<Gender> genderBox;
    private JButton registerButton, backButton;
    private UserManager userManager;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    /**
     * Creates a new RegisterPanel instance.
     *
     * @param userManager the user manager used to handle registration
     * @param cardLayout the layout used to switch between panels
     * @param parentPanel the main container that holds all panels
     */
    public RegisterPanel(UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.userManager = userManager;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new GridLayout(8, 2, 10, 5));
        initGUI();
    }

    /**
     * Initializes the GUI components and sets up the layout and actions.
     * Includes input fields for user data and buttons for register and back.
     */
    private void initGUI() {
        usernameField = new JTextField();
        passwordField = new JTextField();
        ageField = new JTextField();
        heightField = new JTextField();
        weightField = new JTextField();
        nameField = new JTextField();
        genderBox = new JComboBox<>(Gender.values());
        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Height (cm):"));
        add(heightField);
        add(new JLabel("Weight (kg):"));
        add(weightField);
        add(new JLabel("Gender:"));
        add(genderBox);
        add(registerButton);
        add(backButton);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String name = nameField.getText().trim();
            int age = 0, height = 0, weight = 0;

            try {

                age = Integer.parseInt(ageField.getText().trim());
                height = Integer.parseInt(heightField.getText().trim());
                weight = Integer.parseInt(weightField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(RegisterPanel.this,
                        "Please check that age, height and weight are valid numbers.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            Gender gender = (Gender) genderBox.getSelectedItem();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Please enter username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Please enter password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User newUser = new User(username, age, name, height, weight, gender, password);
            boolean success = userManager.registerUser(newUser);

            if (success) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Registration successful!");
                cardLayout.show(parentPanel, "login");
            } else {
                JOptionPane.showMessageDialog(RegisterPanel.this, "User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "login");
        });

    }
}