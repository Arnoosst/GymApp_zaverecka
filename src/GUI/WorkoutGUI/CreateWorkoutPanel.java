package GUI.WorkoutGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateWorkoutPanel extends JPanel {

    private JTextField workoutNameField;
    private JComboBox<WorkoutLevel> levelBox;
    private JButton addExerciseButton;
    private JButton saveButton;
    private JButton backButton;
    private ArrayList<Exercise> exerciseList = new ArrayList<>();
    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;

    public CreateWorkoutPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout(10, 10));
        initGUI();
    }

    private void initGUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        workoutNameField = new JTextField();
        levelBox = new JComboBox<>(WorkoutLevel.values());
        addExerciseButton = new JButton("Add Exercise");
        saveButton = new JButton("Save Workout");
        backButton = new JButton("Back");

        panel.add(new JLabel("Workout Name:"));
        panel.add(workoutNameField);
        panel.add(new JLabel("Level:"));
        panel.add(levelBox);
        panel.add(addExerciseButton);
        panel.add(saveButton);

        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.add(backButton, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        addExerciseButton.addActionListener(e -> {
            ExerciseInputPanel exerciseInputPanel = new ExerciseInputPanel(user, userManager, parentPanel, cardLayout, this);
            parentPanel.add(exerciseInputPanel, "exerciseInput");
            cardLayout.show(parentPanel, "exerciseInput");
        });

        saveButton.addActionListener(e -> {
            String name = workoutNameField.getText().trim();
            WorkoutLevel level = (WorkoutLevel) levelBox.getSelectedItem();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter workout name.");
                return;
            }

            if (exerciseList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Add at least one exercise.");
                return;
            }

            Workout workout = new Workout(name, 0, null, level);
            workout.setExercises(new ArrayList<>(exerciseList));
            user.addCustomWorkout(workout);
            userManager.saveUsers();

            JOptionPane.showMessageDialog(this, "Workout saved successfully!");
            cardLayout.show(parentPanel, "workoutMenu");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "workoutMenu");
        });


    }
    public void addExerciseToWorkout(Exercise ex) {
        exerciseList.add(ex);
        JOptionPane.showMessageDialog(this, "Exercise \"" + ex.getName() + "\" added.");
    }
}

