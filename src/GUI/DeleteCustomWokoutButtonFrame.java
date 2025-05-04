package GUI;

import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class DeleteCustomWokoutButtonFrame extends JFrame {
    private User user;
    private UserManager userManager;
    private JButton infoButton;
    private JButton deleteButton;
    private JButton backButton;


    public DeleteCustomWokoutButtonFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;


        setLayout(new BorderLayout(10, 10));
        setTitle("User: " + user.getUserName() + " - Fitness App -");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(user, userManager);
    }

    private void initGUI(User user, UserManager userManager) {
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));

        for (Workout workout : user.getCustomWorkouts()) {
            JPanel singleWorkoutPanel = new JPanel(new BorderLayout());
            singleWorkoutPanel.setBorder(BorderFactory.createTitledBorder(workout.getName()));

            JPanel buttonPanel = new JPanel();
            JButton infoButton = new JButton("More Info");
            JButton deleteButton = new JButton("Delete");

            buttonPanel.add(infoButton);
            buttonPanel.add(deleteButton);
            singleWorkoutPanel.add(buttonPanel, BorderLayout.EAST);

            infoButton.addActionListener(e -> {
                WorkoutInfoButtonFrame workoutInfoButtonFrame = new WorkoutInfoButtonFrame(workout);
                workoutInfoButtonFrame.setVisible(true);
            });

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    user.removeCustomWorkoout(workout);
                    dispose();
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
            dispose();
        });
    }

}
