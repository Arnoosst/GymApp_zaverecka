package GUI;

import Model.UserManager;
import Model.User;
import Model.Gender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField ageField;
    private JTextField heightField;
    private JTextField weightField;
    private JTextField nameField;
    private JComboBox<Gender> genderBox;
    private JButton registerButton;
    private JButton backButton;
    private UserManager userManager;

    private User user;

    public RegisterFrame(UserManager userManager, User user) {
        this.userManager = userManager;

        setTitle("Register");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI(user);
    }

    private void initUI(User user) {
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 5));

        usernameField = new JTextField();
        passwordField = new JTextField();
        ageField = new JTextField();
        heightField = new JTextField();
        weightField = new JTextField();
        nameField = new JTextField();
        genderBox = new JComboBox<>(Gender.values());
        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        panel.add(new JLabel("Height (cm):"));
        panel.add(heightField);

        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);

        panel.add(new JLabel("Gender:"));
        panel.add(genderBox);

        panel.add(registerButton);
        panel.add(backButton);

        add(panel);


        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                String name = nameField.getText().trim();
                int age, height, weight;

                try {

                    age = Integer.parseInt(ageField.getText().trim());
                    height = Integer.parseInt(heightField.getText().trim());
                    weight = Integer.parseInt(weightField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Please check that age, height and weight are valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Gender gender = (Gender) genderBox.getSelectedItem();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Please enter username.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Please enter password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User newUser = new User(username, age,name, height,weight, gender, password);


                boolean success = userManager.registerUser(newUser);

                if (success) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Registration successful!");
                    new LoginFrame(userManager, user).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(userManager, user).setVisible(true);
                dispose();
            }
        });
    }
}