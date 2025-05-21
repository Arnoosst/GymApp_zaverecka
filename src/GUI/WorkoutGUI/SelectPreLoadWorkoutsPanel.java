package GUI.WorkoutGUI;

import Model.PreparedWorkoutLoader;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel for selecting one of the preloaded workouts from file.
 *
 * Lists workouts from an external file and allows the user to either
 * view more information about each workout or start it.
 *
 * @author Vojtěch
 */
public class SelectPreLoadWorkoutsPanel extends JPanel {

    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private WorkoutPanel workoutPanel2;
    private ArrayList<Workout> workouts;


    /**
     * Constructs the SelectPreLoadWorkoutsPanel.
     *
     * @param user the current user
     * @param userManager the user manager instance
     * @param parentPanel the panel managing the CardLayout views
     * @param cardLayout the CardLayout used for switching views
     * @param workoutPanel2 reference to the workout execution panel
     */
    public SelectPreLoadWorkoutsPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout, WorkoutPanel workoutPanel2) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        this.workoutPanel2 = workoutPanel2;
        this.workouts = PreparedWorkoutLoader.loadPreparedWorkouts("src/data/prepared_workouts.txt");

        setLayout(new BorderLayout(10, 10));
        initGUI();
    }


    /**
     * Initializes the GUI by loading preconfigured workouts and
     * displaying them as selectable items with "More Info" and "Select" buttons.
     */
    private void initGUI() {
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));


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
                WorkoutInfoFrame workoutInfoFrame = new WorkoutInfoFrame(workout);
                workoutInfoFrame.setVisible(true);
            });

            selectButton.addActionListener(e -> {
                WorkoutExecutionFrame workoutExecutionFrame = new WorkoutExecutionFrame(user, userManager, workout, workoutPanel2);
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
            cardLayout.show(parentPanel, "startWorkout");
        });
    }
}

