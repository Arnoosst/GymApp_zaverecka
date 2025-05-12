package GUI.WorkoutGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class ExerciseInputFrame extends JFrame {
    private Exercise result;
    private JTextField nameField;
    private JTextField repsField;
    private JTextField setsField;
    private JTextField weightField;
    private JButton backButton;
    private JButton saveButton;
    private JComboBox<WorkoutLevel> workoutLevelBox;
    private User user;
    private UserManager userManager;

    public Exercise getResult() {
        return result;
    }

    public ExerciseInputFrame(User user, UserManager userManager) {
        setTitle("Create Custom Workout");
        setSize(600, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(user, userManager);
    }

    private void initGUI(User user, UserManager userManager) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 5));

        nameField = new JTextField();
        repsField = new JTextField();
        setsField = new JTextField();
        weightField = new JTextField();
        backButton = new JButton("Back");
        saveButton = new JButton("Save");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Number of Sets:"));
        panel.add(setsField);

        panel.add(new JLabel("Reps per Set:"));
        panel.add(repsField);

        panel.add(new JLabel("Weight per Set (kg):"));
        panel.add(weightField);

        panel.add(saveButton);
        panel.add(backButton);

        add(panel);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();

            int numberOfSets, reps;
            double weight;
            try {
                numberOfSets = Integer.parseInt(setsField.getText().trim());
                reps = Integer.parseInt(repsField.getText().trim());
                weight = Double.parseDouble(weightField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ExerciseInputFrame.this, "Please check that reps, sets and weight are valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(ExerciseInputFrame.this, "Please enter exercise name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Exercise exercise = new Exercise(name);
            exercise.initializeSets(numberOfSets);

            for (ExerciseSets set : exercise.getSets()) {
                set.setReps(reps);
                set.setWeight(weight);
            }

            result = exercise;
            JOptionPane.showMessageDialog(this, "Exercise added successfully!");
            userManager.saveUsers();
            dispose();
        });

        backButton.addActionListener(e -> {
            CreateWorkoutFrame createWorkoutFrame = new CreateWorkoutFrame(user, userManager);
            createWorkoutFrame.setVisible(true);
            dispose();
        });
    }
}