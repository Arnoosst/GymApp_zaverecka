package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JButton addMealButton;
    private JButton addWorkoutButton;
    private JButton userProfileButton;
    private JButton exitButton;

    public MainPanel(ActionListener listener) {
        setLayout(new GridLayout(4, 1, 10, 10)); // 4 řádky, 1 sloupec, mezery 10px

        addMealButton = new JButton("Přidat jídlo");
        addWorkoutButton = new JButton("Přidat trénink");
        userProfileButton = new JButton("Profil uživatele");
        exitButton = new JButton("Ukončit aplikaci");

        addMealButton.addActionListener(listener);
        addWorkoutButton.addActionListener(listener);
        userProfileButton.addActionListener(listener);
        exitButton.addActionListener(listener);

        add(addMealButton);
        add(addWorkoutButton);
        add(userProfileButton);
        add(exitButton);
    }

    public JButton getAddMealButton() {
        return addMealButton;
    }

    public JButton getAddWorkoutButton() {
        return addWorkoutButton;
    }

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
