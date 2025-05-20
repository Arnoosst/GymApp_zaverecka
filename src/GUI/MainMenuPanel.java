package GUI;


import Model.User;
import Model.UserManager;
import Model.Workout;
import Model.WorkoutLevel;
import Model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MainMenuPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public MainMenuPanel(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        setLayout(new GridLayout(3, 1, 10, 10));
        initGUI();
    }

    private void initGUI() {
        JButton userProfileButton = new JButton("User");
        JButton workoutButton = new JButton("Workout");
        JButton mealButton = new JButton("Kalorie");

        add(userProfileButton);
        add(workoutButton);
        add(mealButton);

        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        userProfileButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "user");
        });

        workoutButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "workout");
        });

        mealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "meal");
        });
    }
}
