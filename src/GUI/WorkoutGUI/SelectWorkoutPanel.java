package GUI.WorkoutGUI;

import Model.Exercise;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class SelectWorkoutPanel extends JPanel {

    public SelectWorkoutPanel(Workout workout, User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to start this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (workout.getExercises() == null || workout.getExercises().isEmpty()) {
            JOptionPane.showMessageDialog(this, "This workout has no exercises.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean cancelExercise = false;

        for (int i = 0; i < workout.getExercises().size(); i++) {
            Exercise exercise = workout.getExercises().get(i);
            if (exercise == null) {
                JOptionPane.showMessageDialog(this, "Invalid exercise at position " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            JPanel panelForSetCount = new JPanel(new GridLayout(2, 2, 10, 5));
            JTextField setsField = new JTextField();
            panelForSetCount.add(new JLabel("Enter number of sets for exercise " + (i + 1) + ":"));
            panelForSetCount.add(setsField);

            int result = JOptionPane.showConfirmDialog(this, panelForSetCount, "Set Count", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                int setCount = Integer.parseInt(setsField.getText().trim());
                if (setCount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive number of sets.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    i--;
                    continue;
                }

                exercise.initializeSets(setCount);

                for (int j = 0; j < setCount && !cancelExercise; j++) {
                    JPanel panelForInput = new JPanel(new BorderLayout(5, 5));
                    panelForInput.add(new JLabel(exercise.getName(), JLabel.CENTER), BorderLayout.NORTH);

                    JPanel gridPanel = new JPanel(new GridLayout(2, 2, 5, 5));
                    JTextField repsField = new JTextField();
                    JTextField weightField = new JTextField();

                    gridPanel.add(new JLabel("Reps for set " + (j + 1) + ":"));
                    gridPanel.add(repsField);
                    gridPanel.add(new JLabel("Weight (kg):"));
                    gridPanel.add(weightField);
                    panelForInput.add(gridPanel, BorderLayout.CENTER);

                    result = JOptionPane.showConfirmDialog(this, panelForInput, "Set Details", JOptionPane.OK_CANCEL_OPTION);
                    if (result != JOptionPane.OK_OPTION) {
                        int confirmCancel = JOptionPane.showConfirmDialog(this, "Do you want to cancel this exercise?", "Cancel Exercise", JOptionPane.YES_NO_OPTION);
                        if (confirmCancel == JOptionPane.YES_OPTION) {
                            cancelExercise = true;
                            break;
                        } else {
                            j--;
                            continue;
                        }
                    }

                    try {
                        int reps = Integer.parseInt(repsField.getText().trim());
                        int weight = Integer.parseInt(weightField.getText().trim());
                        if (reps <= 0 || weight < 0) {
                            JOptionPane.showMessageDialog(this, "Please enter valid numbers (reps > 0, weight >= 0).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            j--;
                            continue;
                        }
                        exercise.getSets()[j].setReps(reps);
                        exercise.getSets()[j].setWeight(weight);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        j--;
                    }
                }

                if (cancelExercise) {
                    JOptionPane.showMessageDialog(this, "Workout cancelled.");
                    cardLayout.show(parentPanel, "workoutPanel");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of sets.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                i--;
            }
        }

        if (!cancelExercise) {
            user.addWorkoutToLog(workout);
            userManager.saveUsers();
            JOptionPane.showMessageDialog(this, "Workout completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(parentPanel, "workoutPanel");
        }
    }
}

