package GUI.WorkoutGUI;

import GUI.MainFrame;
import GUI.MealGUI.CaloriesChartMenuFrame;
import Model.PreparedWorkoutLoader;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class WorkoutFrame extends JFrame {

    private User user;
    private UserManager userManager;
    private JButton startWorkoutButton;
    private JButton createCustomWorkoutButton;
    private JButton viewPresetWorkoutsButton;
    private JButton viewCustomWorkoutsButton;
    private JButton deleteCustomWorkoutButton;
    private JButton backButton;
    private JButton ownWorkout;
    private JButton preSetWorkout;

    public WorkoutFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;

        setTitle("Workout Frame");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initGUI(user, userManager);
        setVisible(true);
    }

    private void initGUI(User user, UserManager userManager) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Workout Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        panel.add(new JLabel("Total number of workouts: " + user.getWorkoutLogs().size()));
        panel.add(new JLabel("Total volume lifted: " + user.getTotalVolumeLifted() + " kg"));
        panel.add(new JLabel("Total calories burned: " + user.getTotalCaloriesBurned() + " kcal"));
        panel.add(new JLabel("Total time spent: " + user.getTotalWorkoutHours() + " h"));

        startWorkoutButton = new JButton("Start Workout");
        createCustomWorkoutButton = new JButton("Create Custom Workout");
        viewPresetWorkoutsButton = new JButton("View Preset Workouts");
        viewCustomWorkoutsButton = new JButton("View Custom Workouts");
        deleteCustomWorkoutButton = new JButton("Delete Custom Workout");
        backButton = new JButton("Back");

        panel.add(startWorkoutButton);
        panel.add(createCustomWorkoutButton);
        panel.add(viewPresetWorkoutsButton);
        panel.add(viewCustomWorkoutsButton);
        panel.add(deleteCustomWorkoutButton);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);


        startWorkoutButton.addActionListener(e -> {
            StartWorkoutButtonFrame startWorkoutButtonFrame = new StartWorkoutButtonFrame(user, userManager);
            startWorkoutButtonFrame.setVisible(true);
            dispose();
        });

        createCustomWorkoutButton.addActionListener(e -> {
            CreateWorkoutFrame createCustomWorkoutFrame = new CreateWorkoutFrame(user, userManager);
            createCustomWorkoutFrame.setVisible(true);
            dispose();
        });

        viewPresetWorkoutsButton.addActionListener(e -> {
            JFrame preLoadWorkoutFrame = new JFrame("All Preset Workouts");
            preLoadWorkoutFrame.setSize(400, 300);
            preLoadWorkoutFrame.setLocationRelativeTo(null);
            preLoadWorkoutFrame.setLayout(new BorderLayout(10, 10));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts("src/data/prepared_workouts.txt");
            if (!workouts.isEmpty()) {
                for (Workout workout : workouts) {
                    textArea.append(workout.getName() + ": " + workout.getWorkoutLevel() + "\n\n");
                }
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> preLoadWorkoutFrame.dispose());

            preLoadWorkoutFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
            preLoadWorkoutFrame.add(closeButton, BorderLayout.SOUTH);
            preLoadWorkoutFrame.setVisible(true);
        });

        viewCustomWorkoutsButton.addActionListener(e -> {
            JFrame mealsFrame = new JFrame("All Custom Workouts");
            mealsFrame.setSize(400, 300);
            mealsFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            HashSet<Workout> workouts = this.user.getCustomWorkouts();
            if (workouts != null && !workouts.isEmpty()) {
                for (Workout workout : workouts) {
                    textArea.append(workout.getName() + ": " + workout.getWorkoutLevel() + "\n\n");
                }
            } else {
                textArea.setText("User has no custom workouts yet.");
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> mealsFrame.dispose());

            mealsFrame.add(new JScrollPane(textArea));
            mealsFrame.add(closeButton, BorderLayout.SOUTH);
            mealsFrame.setVisible(true);
        });

        deleteCustomWorkoutButton.addActionListener(e -> {
            DeleteCustomWokoutButtonFrame deleteCustomWokoutButtonFrame = new DeleteCustomWokoutButtonFrame(user, userManager);
            deleteCustomWokoutButtonFrame.setVisible(true);
            dispose();
        });

        backButton.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame(user, userManager);
            mainFrame.setVisible(true);
            dispose();
        });
    }
}