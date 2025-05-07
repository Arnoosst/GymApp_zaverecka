package GUI;

import Model.User;
import Model.UserManager;
import Model.Workout;
import Model.WorkoutLevel;

import javax.swing.*;
        import java.awt.*;

public class StartWorkoutButtonFrame extends JFrame {

    private JButton ownWorkout;
    private JButton preSetWorkout;
    private JButton backButton;
    private User user;
    private UserManager userManager;


    public StartWorkoutButtonFrame(User user, UserManager userManager) {
        this.user = user;
        setTitle("Workout");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI(user, userManager);
    }

    private void initUI(User user, UserManager userManager) {

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        ownWorkout = new JButton("My own workouts");
        preSetWorkout = new JButton("Pre set workouts");
        backButton = new JButton("Back");

        panel.add(ownWorkout);
        panel.add(preSetWorkout);
        panel.add(backButton);


        add(panel);
        setVisible(true);


        ownWorkout.addActionListener(e -> {
            SelectUserWorkoutFrame selectUserWorkoutFrame = new SelectUserWorkoutFrame(user, userManager);
            selectUserWorkoutFrame.setVisible(true);
            dispose();
        });

        preSetWorkout.addActionListener(e -> {
            SelectPreLoadWorkoutsFrame selectPreLoadWorkoutsFrame = new SelectPreLoadWorkoutsFrame(user, userManager);
            setVisible(true);
            dispose();
        });
        backButton.addActionListener(e ->{
            WorkoutFrame workoutFrame = new WorkoutFrame(user, userManager);
            workoutFrame.setVisible(true);
            dispose();
        });
    }


}
