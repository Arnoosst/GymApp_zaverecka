package GUI;

import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class ChangeUserDataFrame extends JFrame {

    private User user;
    private UserManager userManager;
    private UserFrame userFrame;

    public ChangeUserDataFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        setTitle("Change User Data");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI(user, userManager);

    }

    public void initGUI(User user, UserManager userManager) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton nameButton = new JButton("Change Name");
        JButton passwordButton = new JButton("Change Password");
        JButton weightButton = new JButton("Change Weight");
        JButton heightButton = new JButton("Change Height");
        JButton backButton = new JButton("Back");

        nameButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(this, "Enter new name:");
            if (newName != null && !newName.isEmpty()) {
                user.setName(newName);
                userManager.saveUsers();
            }
        });

        passwordButton.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
                userManager.saveUsers();
            }
        });

        weightButton.addActionListener(e -> {
            String newWeight = JOptionPane.showInputDialog(this, "Enter new weight (kg):");
            try {
                double weight = Double.parseDouble(newWeight);
                user.setWeight(weight);
                userManager.saveUsers();
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
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid value.");
            }
        });

        backButton.addActionListener(e -> {
            UserFrame userFrame = new UserFrame(user, userManager);
            userFrame.setVisible(true);
            dispose();
        });

        panel.add(nameButton);
        panel.add(passwordButton);
        panel.add(weightButton);
        panel.add(heightButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }
}