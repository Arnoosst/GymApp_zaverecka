package GUI.WorkoutGUI;

import Model.PreparedWorkoutLoader;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SelectPreLoadWorkoutsPanel extends JPanel {

    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private WorkoutPanel workoutPanel2;

    public SelectPreLoadWorkoutsPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout, WorkoutPanel workoutPanel2) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        this.workoutPanel2 = workoutPanel2;

        setLayout(new BorderLayout(10, 10));
        initGUI(workoutPanel2);
    }

    private void initGUI(WorkoutPanel workoutPanel2) {
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

