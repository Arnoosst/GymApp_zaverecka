package GUI.WorkoutGUI;

import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;


/**
 * Panel for deleting custom workouts.
 *
 * Displays all custom workouts of the user, each with an option
 * to view more info or delete it. Deletion requires user confirmation.
 * After deletion, the panel refreshes to reflect changes.
 *
 * @author Vojtěch
 */
public class DeleteCustomWorkoutPanel extends JPanel {

    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;



    /**
     * Constructs the panel for deleting custom workouts.
     *
     * @param user the current user
     * @param userManager the user manager instance
     * @param parentPanel the main panel with CardLayout
     * @param cardLayout the CardLayout used for switching views
     */
    public DeleteCustomWorkoutPanel(User user, UserManager userManager, JPanel parentPanel, CardLayout cardLayout) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout(10, 10));
        initGUI();
    }



    /**
     * Initializes the GUI components and workout list.
     */
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
                WorkoutInfoFrame workoutInfoFrame = new WorkoutInfoFrame(workout);
            });

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    user.removeCustomWorkoout(workout);
                    userManager.saveUsers();

                    refresh();
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
            cardLayout.show(parentPanel, "workout");
        });
    }



    /**
     * Refreshes the panel after deleting a workout.
     */
    public void refresh() {
        removeAll();
        initGUI();
        revalidate();
        repaint();
    }
}
