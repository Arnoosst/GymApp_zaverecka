package GUI.MealGUI;


import Model.*;

import javax.swing.*;
import java.awt.*;


/**
 * MealPanel is the main panel for handling user meal-related actions such as:
 * logging meals, creating and viewing custom or preset meals, and deleting custom meals.
 *
 * It shows basic user statistics and offers buttons to navigate to other related panels.
 *
 * @author Vojtěch Malínek
 */
public class MealPanel extends JPanel {
    private User user;
    private JPanel infoPanel;
    private ViewCustomMealPanel viewCustomMealPanel;
    private DeleteCustomMealPanel deleteCustomMealPanel;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    /**
     * Constructs the MealPanel with required components and dependencies.
     *
     * @param user                  current user
     * @param userManager           unused but could be used for future extensions
     * @param cardLayout            layout used to switch between views
     * @param parentPanel           parent container for CardLayout
     * @param viewCustomMealPanel  panel used to display custom meals
     * @param deleteCustomMealPanel panel used to delete custom meals
     */
    public MealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel, ViewCustomMealPanel viewCustomMealPanel, DeleteCustomMealPanel deleteCustomMealPanel) {
        this.user = user;
        this.viewCustomMealPanel = viewCustomMealPanel;
        this.deleteCustomMealPanel = deleteCustomMealPanel;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout(10, 10));
        initGUI();
    }

    private void initGUI() {
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


    /**
     * Refreshes the panel by reinitializing all components.
     * Call this if user data has changed and the view needs to be updated.
     */
    public void refresh(){
        removeAll();
        initGUI();
        revalidate();
        repaint();
    }
}
