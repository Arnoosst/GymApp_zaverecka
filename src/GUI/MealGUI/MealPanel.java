package GUI.MealGUI;


import Model.*;

import javax.swing.*;
import java.awt.*;

public class MealPanel extends JPanel {
    public MealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Meal Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        if(user.getMealLogs()== null){
            panel.add(new JLabel("Total number of days you logged: 0 days"));
        }else{
            panel.add(new JLabel("Total number of days you logged: " + user.getMealLogs().size() + " days"));
        }

        panel.add(new JLabel("Total calories burned: " + user.getTotalCaloriesBurned() + " kcal"));

        JButton startLoggingMealButton = new JButton("Start Logging Meal");
        JButton deleteCustomMealButton = new JButton("Delete Custom Meal");
        JButton createCustomMealButton = new JButton("Create Custom Meal");
        JButton viewPresetMealsButton = new JButton("View Preset Meals");
        JButton viewCustomMealsButton = new JButton("View Custom Meals");
        JButton backButton = new JButton("Back");

        panel.add(startLoggingMealButton);
        panel.add(deleteCustomMealButton);
        panel.add(createCustomMealButton);
        panel.add(viewPresetMealsButton);
        panel.add(viewCustomMealsButton);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);

        startLoggingMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "caloriesChartMenu");
        });

        deleteCustomMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "deleteCustomMeal");
        });

        createCustomMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "createCustomMeal");
        });

        viewPresetMealsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewPresetMeal");
        });

        viewCustomMealsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewCustomMeal");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "mainMenu");
        });
    }
}
