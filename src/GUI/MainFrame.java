package GUI;

import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton userProfileButton;
    private JButton workoutButton;
    private JButton mealButton;
    private User user;
    private UserManager userManager;


    public MainFrame(User user, UserManager userManager) {
        this.user = user;
        setTitle("Fitness App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI(user, userManager);
    }

    private void initUI(User user, UserManager userManager) {

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        userProfileButton = new JButton("User");
        workoutButton = new JButton("Workout");
        mealButton = new JButton("Kalorie");

        panel.add(userProfileButton);
        panel.add(workoutButton);
        panel.add(mealButton);


        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        add(panel);
        setVisible(true);


        userProfileButton.addActionListener(e -> {
            UserFrame userFrame = new UserFrame(user, userManager);
            userFrame.setVisible(true);
            dispose();
        });

        workoutButton.addActionListener(e -> {
            user.addCustomWorkout(new Workout("Push Workout", 300, 45, java.time.LocalDate.now(), 2000));
            user.addCustomWorkout(new Workout("Pull Workout", 280, 40, java.time.LocalDate.now(), 1800));
            user.addCustomWorkout(new Workout("Legs Workout", 350, 50, java.time.LocalDate.now(), 2500));
            user.addCustomWorkout(new Workout("Upper Body", 290, 35, java.time.LocalDate.now(), 1600));
            user.addCustomWorkout(new Workout("Lower Body", 320, 45, java.time.LocalDate.now(), 2200));
            user.addCustomWorkout(new Workout("Full Body", 400, 60, java.time.LocalDate.now(), 2800));
            user.addCustomWorkout(new Workout("Core Workout", 200, 30, java.time.LocalDate.now(), 1000));
            user.addCustomWorkout(new Workout("Cardio Session", 350, 45, java.time.LocalDate.now(), 500));
            user.addCustomWorkout(new Workout("HIIT Training", 380, 35, java.time.LocalDate.now(), 800));
            user.addCustomWorkout(new Workout("Strength Training", 270, 50, java.time.LocalDate.now(), 2400));
            user.addCustomWorkout(new Workout("Circuit Training", 330, 40, java.time.LocalDate.now(), 1500));
            WorkoutFrame workoutFrame = new WorkoutFrame(user, userManager);
            workoutFrame.setVisible(true);
            dispose();
        });
    }


}
