package GUI;

import Model.User;
import Model.UserManager;
import Model.Workout;
import Model.WorkoutLevel;
import Model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MainFrame extends JFrame {

    private JButton userProfileButton;
    private JButton workoutButton;
    private JButton mealButton;
    private User user;
    private UserManager userManager;


    public MainFrame(User user, UserManager userManager) {
        this.user = user;
        setTitle("Fitness App");
        setSize(600, 600);
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
            Workout upperBody = new Workout("Upper Body", 45, LocalDate.now(), WorkoutLevel.INTERMEDIATE);
            upperBody.addExercise(new Exercise("Bench Press"));
            upperBody.addExercise(new Exercise("Pull-ups"));
            upperBody.addExercise(new Exercise("Shoulder Press"));

            Workout lowerBody = new Workout("Lower Body", 50, LocalDate.now(), WorkoutLevel.INTERMEDIATE);
            lowerBody.addExercise(new Exercise("Squats"));
            lowerBody.addExercise(new Exercise("Deadlifts"));
            lowerBody.addExercise(new Exercise("Leg Press"));

            user.addCustomWorkout(upperBody);
            user.addCustomWorkout(lowerBody);

            WorkoutFrame workoutFrame = new WorkoutFrame(user, userManager);
            workoutFrame.setVisible(true);
            dispose();
        });
    }


}
