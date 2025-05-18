package GUI.MealGUI;


import Model.*;

import javax.swing.*;
import java.awt.*;

public class MealPanel extends JPanel {
    private User user;
    private JPanel infoPanel;
    private ViewCustomMealPanel viewCustomMealPanel;
    private DeleteCustomMealPanel deleteCustomMealPanel;
    public MealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel, ViewCustomMealPanel viewCustomMealPanel, DeleteCustomMealPanel deleteCustomMealPanel) {
        this.user = user;
        this.viewCustomMealPanel = viewCustomMealPanel;
        this.deleteCustomMealPanel = deleteCustomMealPanel;
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Meal Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        if(user.getMealLogs()== null){
            infoPanel.add(new JLabel("Total number of days you logged: 0 days"));
        }else{
            infoPanel.add(new JLabel("Total number of days you logged: " + user.getMealLogs().size() + " days"));
        }

        infoPanel.add(new JLabel("Total calories burned: " + user.getTotalCaloriesBurned() + " kcal"));

        JButton startLoggingMealButton = new JButton("Start Logging Meal");
        JButton deleteCustomMealButton = new JButton("Delete Custom Meal");
        JButton createCustomMealButton = new JButton("Create Custom Meal");
        JButton viewPresetMealsButton = new JButton("View Preset Meals");
        JButton viewCustomMealsButton = new JButton("View Custom Meals");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        buttonPanel.add(startLoggingMealButton);
        buttonPanel.add(deleteCustomMealButton);
        buttonPanel.add(createCustomMealButton);
        buttonPanel.add(viewPresetMealsButton);
        buttonPanel.add(viewCustomMealsButton);
        buttonPanel.add(backButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        startLoggingMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "caloriesChartMenu");
        });

        deleteCustomMealButton.addActionListener(e -> {
            deleteCustomMealPanel.refresh();
            cardLayout.show(parentPanel, "deleteCustomMeal");
        });

        createCustomMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "createCustomMeal");
        });

        viewPresetMealsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "viewPresetMeal");
        });

        viewCustomMealsButton.addActionListener(e -> {
            viewCustomMealPanel.refresh();
            cardLayout.show(parentPanel, "viewCustomMeal");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "mainMenu");
        });
    }

    private JPanel createInfoPanel(){
        JPanel newPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        newPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        if(user.getMealLogs()== null){
            newPanel.add(new JLabel("Total number of days you logged: 0 days"));
        }else{
            newPanel.add(new JLabel("Total number of days you logged: " + user.getMealLogs().size() + " days"));
        }

        newPanel.add(new JLabel("Total calories burned: " + user.getTotalCaloriesBurned() + " kcal"));
        return newPanel;
    }

    public void refresh(){
        remove(infoPanel);
        infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }
}
