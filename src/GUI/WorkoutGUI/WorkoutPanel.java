package GUI.WorkoutGUI;


import Model.PreparedWorkoutLoader;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;
import java.awt.*;

public class WorkoutPanel extends JPanel {

    private JButton startWorkoutButton;
    private JButton createCustomWorkoutButton;
    private JButton viewPresetWorkoutsButton;
    private JButton viewCustomWorkoutsButton;
    private JButton deleteCustomWorkoutButton;
    private JButton backButton;
    private ViewCustomWorkoutPanel viewCustomWorkoutPanel;
    private DeleteCustomWorkoutPanel deleteCustomWorkoutPanel;

    public WorkoutPanel(User user, JPanel parentPanel, CardLayout cardLayout, ViewCustomWorkoutPanel viewCustomWorkoutPanel, DeleteCustomWorkoutPanel deleteCustomWorkoutPanel) {
        this.viewCustomWorkoutPanel = viewCustomWorkoutPanel;
        this.deleteCustomWorkoutPanel = deleteCustomWorkoutPanel;
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Workout Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        panel.add(new JLabel("Total number of workouts: " + user.getWorkoutLogs().size()));
        panel.add(new JLabel("Total volume lifted: " + user.getTotalVolumeLifted() + " kg"));
        panel.add(new JLabel("Total calories burned: " + user.getTotalCaloriesBurned() + " kcal"));
        panel.add(new JLabel("Total time spent: " + user.getTotalWorkoutHours() + " h"));

        startWorkoutButton = new JButton("Start Workout");
        createCustomWorkoutButton = new JButton("Create Custom Workout");
        viewPresetWorkoutsButton = new JButton("View Preset Workouts");
        viewCustomWorkoutsButton = new JButton("View Custom Workouts");
        deleteCustomWorkoutButton = new JButton("Delete Custom Workout");
        backButton = new JButton("Back");

        panel.add(startWorkoutButton);
        panel.add(createCustomWorkoutButton);
        panel.add(viewPresetWorkoutsButton);
        panel.add(viewCustomWorkoutsButton);
        panel.add(deleteCustomWorkoutButton);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);



        startWorkoutButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "startWorkout");
        });

        createCustomWorkoutButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "createWorkout");
        });

        viewPresetWorkoutsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewPresetWorkouts");
        });

        viewCustomWorkoutsButton.addActionListener(e -> {
            viewCustomWorkoutPanel.refresh();
            cardLayout.show(parentPanel, "viewCustomWorkouts");
        });

        deleteCustomWorkoutButton.addActionListener(e -> {
            deleteCustomWorkoutPanel.refresh();
            cardLayout.show(parentPanel, "deleteWorkout");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "mainMenu");
        });
    }
}
