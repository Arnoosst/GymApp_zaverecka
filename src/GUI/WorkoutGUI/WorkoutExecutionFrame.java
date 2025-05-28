package GUI.WorkoutGUI;

import Model.Exercise;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * A JFrame allowing the user to execute a selected workout
 * by entering the number of sets, reps, and weight for each exercise.
 *
 * @author Vojtěch Malínek
 */
public class WorkoutExecutionFrame extends JFrame {

    private User user;
    private UserManager userManager;
    private WorkoutPanel workoutPanel;

    /**
     * Constructs the WorkoutExecutionFrame for performing a workout.
     *
     * @param user          The current user.
     * @param userManager   The user manager instance.
     * @param workout       The workout to be executed.
     * @param workoutPanel  The panel to be refreshed after workout completion.
     */
    public WorkoutExecutionFrame(User user, UserManager userManager, Workout workout, WorkoutPanel workoutPanel) {
        this.user = user;
        this.userManager = userManager;
        this.workoutPanel = workoutPanel;

        setLayout(new BorderLayout(10, 10));
        setTitle("User: " + user.getUserName() + " - Fitness App -");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(workout);
    }

    /**
     * Initializes the GUI and handles the workout execution process.
     *
     * @param workout The workout to be executed.
     */
    private void initGUI(Workout workout) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to start this workout?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (workout.getExercises() == null || workout.getExercises().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "This workout has no exercises.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean cancelExercise = false;

        for (int i = 0; i < workout.getExercises().size(); i++) {
            Exercise exercise = workout.getExercises().get(i);

            if (exercise == null) {
                JOptionPane.showMessageDialog(this,
                        "Invalid exercise at position " + (i + 1),
                        "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            JPanel setHowManySets = new JPanel(new GridLayout(2, 2, 10, 5));
            JTextField setsField = new JTextField();

            setHowManySets.add(new JLabel("Enter number of sets for exercise " + (i + 1) + ":"));
            setHowManySets.add(setsField);

            int result = JOptionPane.showConfirmDialog(this, setHowManySets,
                    "Set Count", JOptionPane.OK_CANCEL_OPTION);

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            try {
                int setCount = Integer.parseInt(setsField.getText().trim());

                if (setCount <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter a positive number of sets.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    i--;
                    continue;
                }

                exercise.initializeSets(setCount);

                for (int j = 0; j < setCount && !cancelExercise; j++) {
                    JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
                    JLabel exerciseLabel = new JLabel(exercise.getName(), JLabel.CENTER);
                    inputPanel.add(exerciseLabel, BorderLayout.NORTH);

                    JPanel repsPanelWeight = new JPanel(new GridLayout(2, 2, 5, 5));

                    repsPanelWeight.add(new JLabel("Reps for set " + (j + 1) + ":"));
                    JTextField repsField = new JTextField();
                    repsPanelWeight.add(repsField);

                    repsPanelWeight.add(new JLabel("Weight (kg):"));
                    JTextField weightField = new JTextField();
                    repsPanelWeight.add(weightField);

                    inputPanel.add(repsPanelWeight, BorderLayout.CENTER);

                    result = JOptionPane.showConfirmDialog(this, inputPanel,
                            "Set Details", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            int reps = Integer.parseInt(repsField.getText().trim());
                            int weight = Integer.parseInt(weightField.getText().trim());

                            if (reps <= 0 || weight < 0) {
                                JOptionPane.showMessageDialog(this,
                                        "Please enter valid numbers (reps > 0, weight >= 0).",
                                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                j--;
                                continue;
                            }

                            exercise.getSets()[j].setReps(reps);
                            exercise.getSets()[j].setWeight(weight);

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this,
                                    "Please enter valid numbers for reps and weight.",
                                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            j--;
                        }
                    } else {
                        int confirmCancel = JOptionPane.showConfirmDialog(this,
                                "Do you want to cancel this exercise?",
                                "Cancel Exercise", JOptionPane.YES_NO_OPTION);
                        if (confirmCancel == JOptionPane.YES_OPTION) {
                            cancelExercise = true;
                        } else {
                            j--;
                        }
                    }
                }

                if (cancelExercise) {
                    JOptionPane.showMessageDialog(this, "Workout cancelled.");
                    dispose();
                    break;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number of sets.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                i--;
            }
        }

        if (!cancelExercise) {
            workout.setDate(LocalDate.now());
            workout.setDuration(workout.calculateTimeDuration());
            user.addWorkoutToLog(workout);
            userManager.saveUsers();
            workoutPanel.refresh();

            JOptionPane.showMessageDialog(this,
                    "Workout completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}

