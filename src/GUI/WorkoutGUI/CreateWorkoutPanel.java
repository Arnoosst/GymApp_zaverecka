package GUI.WorkoutGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Panel for creating a custom workout.
 *
 * Allows user to input a workout name, select difficulty level,
 * add exercises, and save the workout to their profile.
 *
 * @author Vojtěch
 */
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


    /**
     * Constructs a panel to create a new workout.
     *
     * @param user the current user
     * @param userManager the user manager instance
     * @param parentPanel the parent panel using CardLayout
     * @param cardLayout the layout used to switch views
     */
    public CreateWorkoutPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout(10, 10));
        initGUI();
    }



    /**
     * Initializes the GUI interface.
     */
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
            cardLayout.show(parentPanel, "workout");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "workout");
        });
    }


    /**
     * Adds an exercise to the current workout.
     *
     * @param exercise the exercise to add
     * @return true if exercise was added, false if null
     */
    public boolean addExercise(Exercise exercise) {
        if(exercise != null) {
            exerciseList.add(exercise);
            return true;
        }
        return false;
    }
}

