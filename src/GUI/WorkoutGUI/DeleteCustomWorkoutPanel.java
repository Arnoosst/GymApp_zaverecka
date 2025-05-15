package GUI.WorkoutGUI;

import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class DeleteCustomWorkoutPanel extends JPanel {

    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;

    public DeleteCustomWorkoutPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout) {
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
                //TODO klasika info button
            });

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    user.removeCustomWorkoout(workout);
                    userManager.saveUsers();

                    parentPanel.remove(this);
                    DeleteCustomWorkoutPanel newPanel = new DeleteCustomWorkoutPanel(user, userManager, parentPanel, cardLayout);
                    parentPanel.add(newPanel, "deleteWorkout");
                    cardLayout.show(parentPanel, "deleteWorkout");
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
            cardLayout.show(parentPanel, "workoutMenu");
        });
    }
}
