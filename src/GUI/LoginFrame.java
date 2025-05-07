package GUI;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private User user;

    private UserManager userManager;

    public LoginFrame(UserManager userManager, User user) {
        this.userManager = userManager;

        setTitle("Login");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI(user);
    }

    private void initUI(User user) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));


        usernameField = new JTextField();
        passwordField = new JTextField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);


        add(panel);


        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                User user = userManager.login(username, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                    new MainFrame(user, userManager);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame(userManager, user).setVisible(true);
                dispose();
            }
        });
    }
}