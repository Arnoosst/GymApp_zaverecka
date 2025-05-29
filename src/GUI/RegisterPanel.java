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
            int age = 0;
            int height = 0, weight = 0;

            try {
                age = Integer.parseInt(ageField.getText().trim());
                height = Integer.parseInt(heightField.getText().trim());
                weight = Integer.parseInt(weightField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for age, height and weight.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (age < 12) {
                JOptionPane.showMessageDialog(this, "You must be at least 12 years old to register.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (height < 60) {
                JOptionPane.showMessageDialog(this, "Height must be at least 60 cm.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (weight < 30) {
                JOptionPane.showMessageDialog(this, "Weight must be at least 30 kg.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
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

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!username.matches("^[a-zA-Z0-9]{4,16}$")) {
                JOptionPane.showMessageDialog(this, "Username must be 4–16 characters long and contain only letters and numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long.");
                return;
            }

            if (!password.matches(".*[A-Za-z].*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one letter.");
                return;
            }

            if (!password.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one number.");
                return;
            }


            if (!name.matches("^[A-Za-zÀ-ž ]+$")) {
                JOptionPane.showMessageDialog(this, "Name must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
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