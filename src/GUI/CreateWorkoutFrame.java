package GUI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateWorkoutFrame extends JFrame {
    private JTextField workoutNameField;
    private JComboBox<WorkoutLevel> levelBox;
    private JButton addExerciseButton;
    private JButton saveButton;
    private ArrayList<Exercise> exerciseList = new ArrayList<>();
    private User user;
    private UserManager userManager;

    public CreateWorkoutFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        setTitle("New Custom Workout");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
    }

    private void initGUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        workoutNameField = new JTextField();
        levelBox = new JComboBox<>(WorkoutLevel.values());
        addExerciseButton = new JButton("Add Exercise");
        saveButton = new JButton("Save Workout");

        panel.add(new JLabel("Workout Name:"));
        panel.add(workoutNameField);
        panel.add(new JLabel("Level:"));
        panel.add(levelBox);
        panel.add(addExerciseButton);
        panel.add(saveButton);
        add(panel);

        addExerciseButton.addActionListener(e -> {
            ExerciseInputFrame exerciseInputFrame = new ExerciseInputFrame(user, userManager, null);
            exerciseInputFrame.setVisible(true);
            dispose();
        });

        saveButton.addActionListener(e -> {
            String name = workoutNameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter workout name.");
                return;
            }
            if (exerciseList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Add at least one exercise.");
                return;
            }

            Workout workout = new Workout(name, 0, 0, null, 0, null);
            workout.setExercises(new ArrayList<>(exerciseList));
            user.addCustomWorkout(workout);
            JOptionPane.showMessageDialog(this, "Workout saved successfully!");
            dispose();
        });
    }

    public void addExerciseToWorkout(Exercise ex) {
        exerciseList.add(ex);
    }
}

