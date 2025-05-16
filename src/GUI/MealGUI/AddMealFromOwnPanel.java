package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class AddMealFromOwnPanel extends JPanel {

    public AddMealFromOwnPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

        JPanel mealAddFromOwnPanel = new JPanel();
        mealAddFromOwnPanel.setLayout(new BoxLayout(mealAddFromOwnPanel, BoxLayout.Y_AXIS));
        HashSet<Meal> meals = user.getCustomMeals();

        for (Meal meal : meals) {
            JPanel singleMeal = new JPanel(new BorderLayout());
            singleMeal.setBorder(BorderFactory.createTitledBorder(meal.getName()));

            JPanel buttonPanel = new JPanel();
            JButton infoButton = new JButton("More Info");
            JButton selectButton = new JButton("Select");

            buttonPanel.add(infoButton);
            buttonPanel.add(selectButton);
            singleMeal.add(buttonPanel, BorderLayout.EAST);

            infoButton.addActionListener(e -> {

            });

            selectButton.addActionListener(e -> {
                boolean added = user.addMealToLog(meal);
                if (added) {
                    userManager.saveUsers();
                    JOptionPane.showMessageDialog(this, "Meal added successfully to today’s log.");
                } else {
                    JOptionPane.showMessageDialog(this, "Meal could not be added.",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            });

            mealAddFromOwnPanel.add(singleMeal);
        }

        JScrollPane scrollPane = new JScrollPane(mealAddFromOwnPanel);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);

        backButton.addActionListener(e -> {
            parentPanel.add(new ManageMealsPanel(user, userManager, cardLayout, parentPanel), "manageMeals");
            cardLayout.show(parentPanel, "manageMeals");
        });

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
