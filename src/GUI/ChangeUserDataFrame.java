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
        setTitle("Změna údajů");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton nameButton = new JButton("Změnit jméno");
        JButton passwordButton = new JButton("Změnit heslo");
        JButton weightButton = new JButton("Změnit váhu");
        JButton heightButton = new JButton("Změnit výšku");
        JButton backButton = new JButton("Zpět");

        nameButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(this, "Zadej nové jméno:");
            if (newName != null && !newName.isEmpty()) {
                user.setName(newName);
                userManager.saveUsers();
            }
        });

        passwordButton.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog(this, "Zadej nové heslo:");
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
                userManager.saveUsers();
            }
        });

        weightButton.addActionListener(e -> {
            String newWeight = JOptionPane.showInputDialog(this, "Zadej novou váhu (kg):");
            try {
                double weight = Double.parseDouble(newWeight);
                user.setWeight(weight);
                userManager.saveUsers();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Neplatná hodnota.");
            }
        });

        heightButton.addActionListener(e -> {
            String newHeight = JOptionPane.showInputDialog(this, "Zadej novou výšku (cm):");
            try {
                int height = Integer.parseInt(newHeight);
                user.setHeight(height);
                userManager.saveUsers();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Neplatná hodnota.");
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
