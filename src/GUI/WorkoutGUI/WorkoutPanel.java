package GUI.WorkoutGUI;


import Model.User;

import javax.swing.*;
import java.awt.*;


/**
 * Panel providing user access to workout-related actions and statistics.
 * Displays user statistics and buttons for starting, creating, viewing, and deleting workouts.
 *
 * @Author Vojtěch Malínek
 */
public class WorkoutPanel extends JPanel {

    private JPanel infoPanel;
    private JPanel buttonPanel;
    private JButton startWorkoutButton;
    private JButton createCustomWorkoutButton;
    private JButton viewPresetWorkoutsButton;
    private JButton viewCustomWorkoutsButton;
    private JButton deleteCustomWorkoutButton;
    private JButton backButton;
    private ViewCustomWorkoutPanel viewCustomWorkoutPanel;
    private DeleteCustomWorkoutPanel deleteCustomWorkoutPanel;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    /**
     * Constructs the WorkoutPanel with navigation and data panels.
     *
     * @param user                  Current logged-in user.
     * @param parentPanel           Parent panel using CardLayout.
     * @param cardLayout            Layout manager for switching views.
     * @param viewCustomWorkoutPanel Panel for viewing custom workouts.
     * @param deleteCustomWorkoutPanel Panel for deleting custom workouts.
     */
    public WorkoutPanel(User user, JPanel parentPanel, CardLayout cardLayout, ViewCustomWorkoutPanel viewCustomWorkoutPanel, DeleteCustomWorkoutPanel deleteCustomWorkoutPanel) {
        this.viewCustomWorkoutPanel = viewCustomWorkoutPanel;
        this.deleteCustomWorkoutPanel = deleteCustomWorkoutPanel;
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        setLayout(new BorderLayout(10, 10));
        initGUI();
    }


    /**
     * Initializes GUI components: title, user statistics, and action buttons.
     */
    private void initGUI() {
        JLabel title = new JLabel("Workout Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        infoPanel.add(new JLabel("Total number of workouts: " + user.getWorkoutLogs().size()));
        infoPanel.add(new JLabel("Total volume lifted: " + user.getTotalVolumeLifted() + " kg"));
        infoPanel.add(new JLabel("Total calories burned: " + user.getTotalCaloriesBurned() + " kcal"));
        infoPanel.add(new JLabel("Total time spent: " + user.getTotalWorkoutHours() + " h"));

        startWorkoutButton = new JButton("Start Workout");
        createCustomWorkoutButton = new JButton("Create Custom Workout");
        viewPresetWorkoutsButton = new JButton("View Preset Workouts");
        viewCustomWorkoutsButton = new JButton("View Custom Workouts");
        deleteCustomWorkoutButton = new JButton("Delete Custom Workout");
        backButton = new JButton("Back");

        buttonPanel.add(startWorkoutButton);
        buttonPanel.add(createCustomWorkoutButton);
        buttonPanel.add(viewPresetWorkoutsButton);
        buttonPanel.add(viewCustomWorkoutsButton);
        buttonPanel.add(deleteCustomWorkoutButton);
        buttonPanel.add(backButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);



        startWorkoutButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "startWorkout");
        });

        createCustomWorkoutButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "createWorkout");
        });

        viewPresetWorkoutsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewPresetWorkouts");
        });

        viewCustomWorkoutsButton.addActionListener(e -> {
            viewCustomWorkoutPanel.refresh();
            cardLayout.show(parentPanel, "viewCustomWorkouts");
        });

        deleteCustomWorkoutButton.addActionListener(e -> {
            deleteCustomWorkoutPanel.refresh();
            cardLayout.show(parentPanel, "deleteWorkout");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "mainMenu");
        });
    }


    public void refresh() {
        removeAll();
        initGUI();
        revalidate();
        repaint();
    }


}
