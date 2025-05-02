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

        setTitle("Přihlášení");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI(user);
    }

    private void initUI(User user) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JTextField();
        loginButton = new JButton("Přihlásit se");
        registerButton = new JButton("Registrovat");

        panel.add(new JLabel("Uživatelské jméno:"));
        panel.add(usernameField);
        panel.add(new JLabel("Heslo:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        // Přihlášení
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                User user = userManager.login(username, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Přihlášení úspěšné!");
                    new MainFrame(user, userManager); // otevře hlavní menu
                    dispose(); // zavře okno
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Uživatel nenalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Registrace
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame(userManager, user).setVisible(true);
                dispose(); // zavře přihlašovací okno
            }
        });
    }
}