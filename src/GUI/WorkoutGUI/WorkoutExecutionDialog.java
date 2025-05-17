package GUI.WorkoutGUI;

import Model.Exercise;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class WorkoutExecutionDialog extends JDialog {

    public WorkoutExecutionDialog(JFrame parent, Workout workout, User user, UserManager userManager) {
        super(parent, "Start Workout", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        if (workout.getExercises() == null || workout.getExercises().isEmpty()) {
            JOptionPane.showMessageDialog(this, "This workout has no exercises.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Start this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            dispose();
            return;
        }

        for (Exercise exercise : workout.getExercises()) {
            if (exercise == null) continue;

            int setCount = askSetCount(exercise.getName());
            if (setCount <= 0) {
                dispose();
                return;
            }

            exercise.initializeSets(setCount);

            for (int j = 0; j < setCount; j++) {
                boolean validInput = false;
                while (!validInput) {
                    JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
                    JTextField repsField = new JTextField();
                    JTextField weightField = new JTextField();

                    inputPanel.add(new JLabel("Reps for set " + (j + 1) + ":"));
                    inputPanel.add(repsField);
                    inputPanel.add(new JLabel("Weight (kg):"));
                    inputPanel.add(weightField);

                    int res = JOptionPane.showConfirmDialog(this, inputPanel, exercise.getName(), JOptionPane.OK_CANCEL_OPTION);
                    if (res != JOptionPane.OK_OPTION) {
                        dispose();
                        return;
                    }

                    try {
                        int reps = Integer.parseInt(repsField.getText().trim());
                        int weight = Integer.parseInt(weightField.getText().trim());

                        if (reps <= 0 || weight < 0) throw new NumberFormatException();

                        exercise.getSets()[j].setReps(reps);
                        exercise.getSets()[j].setWeight(weight);
                        validInput = true;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        user.addWorkoutToLog(workout);
        userManager.saveUsers();

        // Zobraz úspěšnou zprávu a pak uzavři okno ve správném vlákně
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "Workout completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }

    private int askSetCount(String exerciseName) {
        JTextField setsField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JLabel("Enter number of sets for: " + exerciseName));
        panel.add(setsField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Set Count", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return -1;

        try {
            int count = Integer.parseInt(setsField.getText().trim());
            return count > 0 ? count : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
