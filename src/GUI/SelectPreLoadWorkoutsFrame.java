package GUI;

import Model.PreparedWorkoutLoader;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectPreLoadWorkoutsFrame extends JFrame {
    private User user;
    private UserManager userManager;
    private JButton infoButton;
    private JButton selectButton;
    private JButton backButton;

    public SelectPreLoadWorkoutsFrame(User user) {
        this.user = user;


        setLayout(new BorderLayout(10, 10));
        setTitle("User: " + user.getUserName() + " - Fitness App -");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
    }

    public void initGUI() {
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));
        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts("src/data/prepared_workouts.txt");


        for (Workout workout : workouts) {
            JPanel singleWorkoutPanel = new JPanel(new BorderLayout());
            singleWorkoutPanel.setBorder(BorderFactory.createTitledBorder(workout.getName()));

            JPanel buttonPanel = new JPanel();
            JButton infoButton = new JButton("More Info");
            JButton selectButton = new JButton("Select");

            buttonPanel.add(infoButton);
            buttonPanel.add(selectButton);
            singleWorkoutPanel.add(buttonPanel, BorderLayout.EAST);

            infoButton.addActionListener(e -> {
                WorkoutInfoButtonFrame workoutInfoButtonFrame = new WorkoutInfoButtonFrame(workout);
                workoutInfoButtonFrame.setVisible(true);
            });

            selectButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to start this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Check if the workout has exercises
                    if (workout.getExercises() == null || workout.getExercises().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "This workout has no exercises.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean cancelExercise = false;
                    for(int i = 0; i < workout.getExercises().size(); i++) {

                        if (workout.getExercises().get(i) == null) {
                            JOptionPane.showMessageDialog(this, "Invalid exercise at position " + (i+1),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }

                        JPanel panelForSetCount = new JPanel(new GridLayout(2, 2, 10, 5));
                        JTextField setsField = new JTextField();
                        panelForSetCount.add(new JLabel("Enter number of sets for exercise " + (i+1) + ":"));
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




                                for(int j = 0; j < setCount && !cancelExercise; j++) {

                                    JPanel panelForInput = new JPanel(new GridLayout(2, 2, 5, 5));
                                    JTextField repsField = new JTextField();
                                    JTextField weightField = new JTextField();

                                    panelForInput.add(new JLabel("Reps for set " + (j+1) + ":"));
                                    panelForInput.add(repsField);
                                    panelForInput.add(new JLabel("Weight (kg):"));
                                    panelForInput.add(weightField);

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
                                                j--; // Retry this set
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
                                    WorkoutFrame workoutFrame = new WorkoutFrame(user, userManager);
                                    workoutFrame.setVisible(true);
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
                    if(!cancelExercise) {
                        user.addWorkoutToLog(workout);
                        JOptionPane.showMessageDialog(this, "Workout completed!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        WorkoutFrame workoutFrame = new WorkoutFrame(user, userManager);
                        workoutFrame.setVisible(true);
                        dispose();
                    }
                }
            });

            workoutPanel.add(singleWorkoutPanel);
        }

        JScrollPane scrollPane = new JScrollPane(workoutPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            WorkoutFrame workoutFrame = new WorkoutFrame(user, userManager);
            workoutFrame.setVisible(true);
            dispose();
        });
    }

}
