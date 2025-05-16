/*package GUI.WorkoutGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class ExerciseInputPanel extends JPanel {

    private Exercise result;
    private JTextField nameField;
    private JTextField repsField;
    private JTextField setsField;
    private JTextField weightField;
    private JButton backButton;
    private JButton saveButton;
    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private CreateWorkoutPanel returnPanel;

    public ExerciseInputPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout, CreateWorkoutPanel returnPanel) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        this.returnPanel = returnPanel;
        setLayout(new BorderLayout(10, 10));
        initGUI();
    }

    private void initGUI() {
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

        add(panel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            int numberOfSets, reps;
            double weight;

            try {
                numberOfSets = Integer.parseInt(setsField.getText().trim());
                reps = Integer.parseInt(repsField.getText().trim());
                weight = Double.parseDouble(weightField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please check that reps, sets and weight are valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter exercise name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Exercise exercise = new Exercise(name);
            exercise.initializeSets(numberOfSets);

            for (ExerciseSets set : exercise.getSets()) {
                set.setReps(reps);
                set.setWeight(weight);
            }

            JOptionPane.showMessageDialog(this, "Exercise added successfully!");
            userManager.saveUsers();

            returnPanel.addExerciseToWorkout(exercise); // pošleme zpět výsledek
            cardLayout.show(parentPanel, "createWorkout");
        });


        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "createWorkout");
        });
    }

    public Exercise getResult() {
        return result;
    }
}*/
