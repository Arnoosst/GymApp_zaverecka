package GUI.UserGUI;

import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;


public class ChangeUserDataPanel extends JPanel {
    private User user;
    private UserManager userManager;

    public ChangeUserDataPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.userManager = userManager;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton nameButton = new JButton("Change Name");
        JButton passwordButton = new JButton("Change Password");
        JButton weightButton = new JButton("Change Weight");
        JButton heightButton = new JButton("Change Height");
        JButton backButton = new JButton("Back");

        nameButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(this, "Enter new name:");
            if (newName != null && !newName.trim().isEmpty()) {
                user.setName(newName.trim());
                userManager.saveUsers();
                JOptionPane.showMessageDialog(this, "Name updated.");
            }
        });

        passwordButton.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                user.setPassword(newPassword.trim());
                userManager.saveUsers();
                JOptionPane.showMessageDialog(this, "Password updated.");
            }
        });

        weightButton.addActionListener(e -> {
            String newWeight = JOptionPane.showInputDialog(this, "Enter new weight (kg):");
            try {
                double weight = Double.parseDouble(newWeight);
                user.setWeight(weight);
                userManager.saveUsers();
                JOptionPane.showMessageDialog(this, "Weight updated.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid value.");
            }
        });

        heightButton.addActionListener(e -> {
            String newHeight = JOptionPane.showInputDialog(this, "Enter new height (cm):");
            try {
                int height = Integer.parseInt(newHeight);
                user.setHeight(height);
                userManager.saveUsers();
                JOptionPane.showMessageDialog(this, "Height updated.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid value.");
            }
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "user");
        });

        panel.add(nameButton);
        panel.add(passwordButton);
        panel.add(weightButton);
        panel.add(heightButton);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);
    }
}
