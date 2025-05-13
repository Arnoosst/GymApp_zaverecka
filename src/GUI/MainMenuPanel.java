package GUI;

import GUI.MealGUI.CaloriesChartMenuFrame;
import GUI.MealGUI.MealFrame;
import GUI.UserGUI.UserFrame;
import GUI.WorkoutGUI.WorkoutFrame;
import Model.User;
import Model.UserManager;
import Model.Workout;
import Model.WorkoutLevel;
import Model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton userProfileButton = new JButton("User");
        JButton workoutButton = new JButton("Workout");
        JButton mealButton = new JButton("Kalorie");

        add(userProfileButton);
        add(workoutButton);
        add(mealButton);

        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

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
