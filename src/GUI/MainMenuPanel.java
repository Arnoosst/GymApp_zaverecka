package GUI;


import GUI.MealGUI.MealPanel;
import Model.User;
import Model.UserManager;
import Model.Workout;
import Model.WorkoutLevel;
import Model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


/**
 * Main menu panel that provides navigation buttons to access different parts of the application.
 * Includes buttons for user profile, workouts, and meals.
 *
 * Author: Vojtěch Malínek
 */
public class MainMenuPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private MealPanel mealPanel;


    /**
     * Creates a new MainMenuPanel.
     *
     * @param cardLayout the layout manager used to switch panels
     * @param parentPanel the container that holds the panels
     */
    public MainMenuPanel(CardLayout cardLayout, JPanel parentPanel, MealPanel mealPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.mealPanel = mealPanel;
        setLayout(new GridLayout(3, 1, 10, 10));
        initGUI();
    }


    /**
     * Initializes the GUI components including buttons for user profile,
     * workout management, and meal tracking. Sets their respective actions
     * to switch to the corresponding panels.
     */
    private void initGUI() {
        JButton userProfileButton = new JButton("User");
        JButton workoutButton = new JButton("Workout");
        JButton mealButton = new JButton("Calories");

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
            mealPanel.refresh();
            cardLayout.show(parentPanel, "meal");
        });
    }
}
