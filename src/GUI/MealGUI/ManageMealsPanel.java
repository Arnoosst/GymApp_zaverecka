package GUI.MealGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;


/**
 * ManageMealsPanel provides a user interface for managing meals. It allows users to:
 *
 * This panel is intended to be used inside a CardLayout-based navigation system.
 *
 * @author Vojtěch Malínek
 */
public class ManageMealsPanel extends JPanel {
    private CaloriesChartMenuPanel caloriesChartMenuPanel;
    private UserManager userManager;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private AddMealFromOwnPanel addMealFromOwnPanel;


    /**
     * Constructs the ManageMealsPanel.
     *
     * @param user the current user
     * @param userManager the user manager instance
     * @param cardLayout the layout controller for switching views
     * @param parentPanel the container that uses CardLayout
     * @param caloriesChartMenuPanel the panel to return to after meal management
     */
    public ManageMealsPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel, CaloriesChartMenuPanel caloriesChartMenuPanel, AddMealFromOwnPanel addMealFromOwnPanel) {
        this.caloriesChartMenuPanel = caloriesChartMenuPanel;
        this.userManager = userManager;
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.addMealFromOwnPanel = addMealFromOwnPanel;

        setLayout(new BorderLayout());
        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addMealButton = new JButton("Add Meal");
        JButton addMealFromPreLoadButton = new JButton("Add Meal From Pre-Load");
        JButton addMealFromOwnMealsButton = new JButton("Add Meal From Own Meals");
        JButton backButton = new JButton("Back");

        mainPanel.add(addMealButton);
        mainPanel.add(addMealFromPreLoadButton);
        mainPanel.add(addMealFromOwnMealsButton);
        mainPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);

        addMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "addMeal");
        });

        addMealFromPreLoadButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "addMealFromPreLoad");
        });

        addMealFromOwnMealsButton.addActionListener(e -> {
            addMealFromOwnPanel.refresh();
            cardLayout.show(parentPanel, "addMealFromOwn");
        });

        backButton.addActionListener(e -> {
            caloriesChartMenuPanel.refresh();
            cardLayout.show(parentPanel, "caloriesChartMenu");
        });
    }
}
