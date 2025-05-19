package GUI.WorkoutGUI;

import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class WorkoutExecutionFrame extends JFrame {
    private User user;
    private UserManager userManager;
    private JButton infoButton;
    private JButton selectButton;
    private JButton backButton;
    private WorkoutPanel workoutPanel;

    public WorkoutExecutionFrame(User user, UserManager userManager, Workout workout, WorkoutPanel workoutPanel) {
        this.user = user;
        this.userManager = userManager;
        this.workoutPanel = workoutPanel;


        setLayout(new BorderLayout(10, 10));
        setTitle("User: " + user.getUserName() + " - Fitness App -");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(user, userManager, workout, workoutPanel);

    }

    public void initGUI(User user, UserManager userManager, Workout workout, WorkoutPanel workoutPanel2) {
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to start this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {

            if (workout.getExercises() == null || workout.getExercises().isEmpty()) {
                JOptionPane.showMessageDialog(this, "This workout has no exercises.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean cancelExercise = false;
            for (int i = 0; i < workout.getExercises().size(); i++) {

                if (workout.getExercises().get(i) == null) {
                    JOptionPane.showMessageDialog(this, "Invalid exercise at position " + (i + 1),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                JPanel panelForSetCount = new JPanel(new GridLayout(2, 2, 10, 5));
                JTextField setsField = new JTextField();
                panelForSetCount.add(new JLabel("Enter number of sets for exercise " + (i + 1) + ":"));
                panelForSetCount.add(setsField);

                int result = JOptionPane.showConfirmDialog(this, panelForSetCount,
                        "Set Count", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        int setCount = Integer.parseInt(setsField.getText().trim());
                        if (setCount <= 0) {
                            JOptionPane.showMessageDialog(this, "Please enter a positive number of sets.",
                                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            i--;
                            continue;
                        }

                        workout.getExercises().get(i).initializeSets(setCount);


                        for (int j = 0; j < setCount && !cancelExercise; j++) {

                            JPanel panelForInput = new JPanel(new BorderLayout(5, 5));

                            JLabel exerciseLabelName = new JLabel(workout.getExercises().get(i).getName(), JLabel.CENTER);
                            panelForInput.add(exerciseLabelName, BorderLayout.NORTH);


                            JPanel gridPanel = new JPanel(new GridLayout(2, 2, 5, 5));

                            gridPanel.add(new JLabel("Reps for set " + (j + 1) + ":"));
                            JTextField repsField = new JTextField();
                            gridPanel.add(repsField);

                            gridPanel.add(new JLabel("Weight (kg):"));
                            JTextField weightField = new JTextField();
                            gridPanel.add(weightField);

                            panelForInput.add(gridPanel, BorderLayout.CENTER);

                            result = JOptionPane.showConfirmDialog(this, panelForInput,
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

                                    workout.getExercises().get(i).getSets()[j].setReps(reps);
                                    workout.getExercises().get(i).getSets()[j].setWeight(weight);
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(this,
                                            "Please enter valid numbers for reps and weight.",
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                    j--;
                                }
                            } else {

                                int confirmCancel = JOptionPane.showConfirmDialog(this,
                                        "Do you want to cancel this exercise?",
                                        "Cancel Exercise",
                                        JOptionPane.YES_NO_OPTION);
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
                        JOptionPane.showMessageDialog(this, "Please enter a valid number of sets.",
                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        i--;
                    }
                } else {
                    return;
                }
            }
            if (!cancelExercise) {
                workout.setDate(LocalDate.now());
                workout.setDuration(workout.calculateTimeDuration());
                user.addWorkoutToLog(workout);
                userManager.saveUsers();
                workoutPanel2.refresh();
                JOptionPane.showMessageDialog(this, "Workout completed!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }

        }


    }
}
