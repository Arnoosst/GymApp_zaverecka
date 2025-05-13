package GUI;

import Model.UserManager;
import Model.User;
import Model.Gender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private JTextField usernameField, passwordField, ageField, heightField, weightField, nameField;
    private JComboBox<Gender> genderBox;
    private JButton registerButton, backButton;

    public RegisterPanel(UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new GridLayout(8, 2, 10, 5));

        usernameField = new JTextField();
        passwordField = new JTextField();
        ageField = new JTextField();
        heightField = new JTextField();
        weightField = new JTextField();
        nameField = new JTextField();
        genderBox = new JComboBox<>(Gender.values());
        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        add(new JLabel("Username:")); add(usernameField);
        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Password:")); add(passwordField);
        add(new JLabel("Age:")); add(ageField);
        add(new JLabel("Height (cm):")); add(heightField);
        add(new JLabel("Weight (kg):")); add(weightField);
        add(new JLabel("Gender:")); add(genderBox);
        add(registerButton); add(backButton);

        registerButton.addActionListener(e -> {
            // validace + registrace
            // přepnout panel
            cardLayout.show(parentPanel, "loginPanel");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "loginPanel");
        });
    }
}