package GUI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class ExerciseInputFrame extends JFrame {
    private JTextField nameField;
    private JTextField repsField;
    private JTextField setsField;
    private JTextField weightField;
    private JButton backButton;
    private JButton saveButton;
    private JComboBox<WorkoutLevel> workoutLevelBox;
    private User user;
    private UserManager userManager;
    private Workout workout;


    public ExerciseInputFrame(User user, UserManager userManager, Workout workout) {
        setTitle("Create Custom Workout");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(user, userManager, workout);
    }

    private void initGUI(User user, UserManager userManager, Workout workout) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 5));

        nameField = new JTextField();
        repsField = new JTextField();
        setsField = new JTextField();
        weightField = new JTextField();
        backButton = new JButton("Back");
        saveButton = new JButton("Save");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Reps:"));
        panel.add(repsField);

        panel.add(new JLabel("Sets:"));
        panel.add(setsField);

        panel.add(new JLabel("Weight:"));
        panel.add(weightField);

        
        panel.add(saveButton);

        panel.add(backButton);

        add(panel);
        
        
        saveButton.addActionListener(e -> {
            String name = nameField.getText();

            int reps, sets;
            double weight;
            try {
                reps = Integer.parseInt(repsField.getText().trim());
                sets = Integer.parseInt(setsField.getText().trim());
                weight = Double.parseDouble(weightField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ExerciseInputFrame.this, "Please check that reps, sets and weight are valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(ExerciseInputFrame.this, "Please enter exercise name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Exercise exercise = new Exercise(name, sets, reps, weight);


            boolean success = workout.addExercise(exercise);

            if (success) {
                JOptionPane.showMessageDialog(ExerciseInputFrame.this, "Pridani uspesne!");
                new LoginFrame(userManager, user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(ExerciseInputFrame.this, "Neuspesne pridani.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            CreateWorkoutFrame createWorkoutFrame = new CreateWorkoutFrame(user, userManager);
            createWorkoutFrame.setVisible(true);
            dispose();
        });
    }
}
