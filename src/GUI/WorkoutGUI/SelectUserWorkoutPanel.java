package GUI.WorkoutGUI;

import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class SelectUserWorkoutPanel extends JPanel {

    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;

    public SelectUserWorkoutPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout(10, 10));
        initGUI();
    }

    private void initGUI() {
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));

        HashSet<Workout> customWorkouts = user.getCustomWorkouts();

        for (Workout workout : customWorkouts) {
            JPanel singleWorkoutPanel = new JPanel(new BorderLayout());
            singleWorkoutPanel.setBorder(BorderFactory.createTitledBorder(workout.getName()));

            JPanel buttonPanel = new JPanel();
            JButton infoButton = new JButton("More Info");
            JButton selectButton = new JButton("Select");

            buttonPanel.add(infoButton);
            buttonPanel.add(selectButton);
            singleWorkoutPanel.add(buttonPanel, BorderLayout.EAST);

            infoButton.addActionListener(e -> {
                //TODO switch na ifo button
            });

            selectButton.addActionListener(e -> {
                // TODO: Přepnout na SelectWorkoutPanel s daným workoutem
                // Např.:
                // SelectWorkoutPanel selectWorkoutPanel = new SelectWorkoutPanel(workout, user, userManager, parentPanel, cardLayout);
                // parentPanel.add(selectWorkoutPanel, "selectWorkout");
                // cardLayout.show(parentPanel, "selectWorkout");
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
