package GUI.UserGUI;

import GUI.MainMenuPanel;
import GUI.MealGUI.ViewCustomMealPanel;
import GUI.WorkoutGUI.ViewCustomWorkoutPanel;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private User user;
    private UserManager userManager;

    public UserPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.userManager = userManager;

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("User: " + user.getUserName(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("User Information"));
        infoPanel.add(new JLabel("Username: " + user.getUserName()));
        infoPanel.add(new JLabel("Name: " + user.getName()));
        infoPanel.add(new JLabel("Age: " + user.getAge() + " years"));
        infoPanel.add(new JLabel("Height: " + user.getHeight() + " cm"));
        infoPanel.add(new JLabel("Weight: " + user.getWeight() + " kg"));
        infoPanel.add(new JLabel("Gender: " + user.getGender()));
        if(user.getMealLogs()== null){
            infoPanel.add(new JLabel("Calorie logs: 0 days"));
        }else{
            infoPanel.add(new JLabel("Calorie logs: " + user.getMealLogs().size() + " days"));
        }

        infoPanel.add(new JLabel("Workout logs: " + user.getWorkoutLogs().size() + " days"));

        JButton bmrButton = new JButton("Show BMR");
        bmrButton.addActionListener(e -> {
            double bmr = user.calculateBMR(user);
            JOptionPane.showMessageDialog(this, "Your BMR is: " + bmr, "BMR Calculation", JOptionPane.INFORMATION_MESSAGE);
        });
        infoPanel.add(bmrButton);

        JPanel actionsPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton changeDataButton = new JButton("Change details");
        changeDataButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "changeUserData");
        });

        JButton showMealLogsButton = new JButton("Show meal logs");
        showMealLogsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewMealLogs");
        });

        JButton showWorkoutLogsButton = new JButton("Show workout logs");
        showWorkoutLogsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewWorkoutLogs");
        });

       /* JButton showUsersMealsButton = new JButton("Show custom meals");
        showUsersMealsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewCustomMeals");
        });

        JButton showUsersWorkoutsButton = new JButton("Show custom workouts");
        showUsersWorkoutsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewCustomWorkouts");
        }); */

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "mainMenu");
        });

        actionsPanel.add(changeDataButton);
        actionsPanel.add(showMealLogsButton);
        actionsPanel.add(showWorkoutLogsButton);
       // actionsPanel.add(showUsersMealsButton);
       // actionsPanel.add(showUsersWorkoutsButton);
        actionsPanel.add(backButton);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.add(infoPanel);
        centerPanel.add(actionsPanel);
        add(centerPanel, BorderLayout.CENTER);
    }
}
