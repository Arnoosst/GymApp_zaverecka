package GUI.UserGUI;

import GUI.MainFrame;
import Model.Meal;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UserFrame extends JFrame {
    private User user;
    private UserManager userManager;
    private JButton bmrButtonButton;
    private JButton showMealLogsButton;
    private JButton showWorkoutLogsButton;
    private JButton changeDataButtonButton;
    private JButton showUsersMealsButton;
    private JButton showUsersWorkoutsButton;
    private JButton backButtonButton;

    public UserFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;

        setLayout(new BorderLayout(10, 10));
        setTitle("User: " + user.getUserName() + " - Fitness App -");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(user, userManager);
    }


    public void initGUI(User user, UserManager userManager) {
        bmrButtonButton = new JButton("Show BMR");
        showMealLogsButton = new JButton("Show calorie logs");
        showWorkoutLogsButton = new JButton("Show workout logs");
        changeDataButtonButton = new JButton("Change details");
        showUsersMealsButton = new JButton("Show custom meals");
        showUsersWorkoutsButton = new JButton("Show custom workouts");
        backButtonButton = new JButton("Back");


        JLabel title = new JLabel("User information", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);


        JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("User information"));
        panel.add(new JLabel("Username: " + this.user.getUserName()));
        panel.add(new JLabel("Name: " + this.user.getName()));
        panel.add(new JLabel("Age: " + this.user.getAge() + " years"));
        panel.add(new JLabel("Height: " + this.user.getHeight() + " cm"));
        panel.add(new JLabel("Weight: " + this.user.getWeight() + " kg"));
        panel.add(new JLabel("Gender: " + this.user.getGender()));
        panel.add(new JLabel("Calorie logs: " + this.user.getMealLogs().size() + " days"));
        panel.add(new JLabel("Workout logs: " + this.user.getWorkoutLogs().size() + " days"));
        panel.add(bmrButtonButton);


        JPanel panel2 = new JPanel(new GridLayout(6, 1, 5, 5));
        panel2.setBorder(BorderFactory.createTitledBorder("Actions"));
        panel2.add(changeDataButtonButton);
        panel2.add(showMealLogsButton);
        panel2.add(showWorkoutLogsButton);
        panel2.add(showUsersMealsButton);
        panel2.add(showUsersWorkoutsButton);
        panel2.add(backButtonButton);


        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.add(panel);
        centerPanel.add(panel2);


        bmrButtonButton.addActionListener(e -> {
            double bmr = this.user.calculateBMR(this.user);
            JOptionPane.showMessageDialog(this, "Your BMR is: " + bmr, "BMR Calculation", JOptionPane.INFORMATION_MESSAGE);
        });
        showMealLogsButton.addActionListener(e -> {
            JFrame mealFrame = new JFrame("meals");
            mealFrame.setSize(400, 300);
            mealFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            HashMap<LocalDate, ArrayList<Meal>> meals = this.user.getMealLogs();
            if (meals != null && !meals.isEmpty()) {
                for (ArrayList<Meal> mealList : meals.values()) {
                    for (Meal meal : mealList) {
                        textArea.append(meal.toString() + "\n\n");
                    }
                }
            } else {
                textArea.setText("No meals.");
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> mealFrame.dispose());

            mealFrame.add(new JScrollPane(textArea));
            mealFrame.add(closeButton, BorderLayout.SOUTH);
            mealFrame.setVisible(true);
        });

        showWorkoutLogsButton.addActionListener(e -> {
            JFrame mealFrame = new JFrame("workouts");
            mealFrame.setSize(400, 300);
            mealFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            ArrayList<Workout> workouts = this.user.getWorkoutLogs();
            if (workouts != null && !workouts.isEmpty()) {
                for (Workout workout : workouts) {
                    textArea.append(workout.toString() + "\n\n");
                }
            } else {
                textArea.setText("No workouts");
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> mealFrame.dispose());

            mealFrame.add(new JScrollPane(textArea));
            mealFrame.add(closeButton, BorderLayout.SOUTH);
            mealFrame.setVisible(true);
        });

        changeDataButtonButton.addActionListener(e -> {
            ChangeUserDataFrame changeUserDataFrame = new ChangeUserDataFrame(user, userManager);
            changeUserDataFrame.setVisible(true);
            dispose();
        });
        showUsersMealsButton.addActionListener(e -> {
            JFrame mealsFrame = new JFrame("All user meals");
            mealsFrame.setSize(400, 300);
            mealsFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            HashSet<Meal> meals = this.user.getCustomMeals();
            if (meals != null && !meals.isEmpty()) {
                for (Meal meal : meals) {
                    textArea.append(meal.toString() + "\n\n");
                }
            } else {
                textArea.setText("No custom meals.");
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> mealsFrame.dispose());

            mealsFrame.add(new JScrollPane(textArea));
            mealsFrame.add(closeButton, BorderLayout.SOUTH);
            mealsFrame.setVisible(true);
        });
        showUsersWorkoutsButton.addActionListener(e -> {
            JFrame mealsFrame = new JFrame("All user workouts");
            mealsFrame.setSize(400, 300);
            mealsFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            HashSet<Workout> workouts = this.user.getCustomWorkouts();
            if (workouts != null && !workouts.isEmpty()) {
                for (Workout workout : workouts) {
                    textArea.append(workout.toString() + "\n\n");
                }
            } else {
                textArea.setText("User has no workouts yet.");
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> mealsFrame.dispose());

            mealsFrame.add(new JScrollPane(textArea));
            mealsFrame.add(closeButton, BorderLayout.SOUTH);
            mealsFrame.setVisible(true);
        });


        backButtonButton.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame(this.user, this.userManager);
            mainFrame.setVisible(true);
            dispose();
        });


        add(centerPanel, BorderLayout.CENTER);
    }

}