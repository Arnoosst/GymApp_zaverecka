package GUI.MealGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class ManageMealsPanel extends JPanel {

    public ManageMealsPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

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
            parentPanel.add(new AddMealPanel(user, userManager, cardLayout, parentPanel), "addMeal");
            cardLayout.show(parentPanel, "addMeal");
        });

        addMealFromPreLoadButton.addActionListener(e -> {
            parentPanel.add(new AddMealFromPreLoadPanel(user, userManager, cardLayout, parentPanel), "addPreLoadMeal");
            cardLayout.show(parentPanel, "addPreLoadMeal");
        });

        addMealFromOwnMealsButton.addActionListener(e -> {
            parentPanel.add(new AddMealFromOwnPanel(user, userManager, cardLayout, parentPanel), "addOwnMeal");
            cardLayout.show(parentPanel, "addOwnMeal");
        });

        backButton.addActionListener(e -> {
            parentPanel.add(new CaloriesChartMenuPanel(user, userManager, cardLayout, parentPanel), "caloriesChart");
            cardLayout.show(parentPanel, "caloriesChart");
        });
    }
}
