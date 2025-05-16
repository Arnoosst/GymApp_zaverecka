package GUI.MealGUI;

import Model.Meal;
import Model.PreparedMealLoader;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddMealFromPreLoadPanel extends JPanel {

    public AddMealFromPreLoadPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

        JPanel mealAddFromPreLoadPanel = new JPanel();
        mealAddFromPreLoadPanel.setLayout(new BoxLayout(mealAddFromPreLoadPanel, BoxLayout.Y_AXIS));
        ArrayList<Meal> meals = PreparedMealLoader.loadMealsFromFile("src/data/prepared_meals.txt");

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
                MealInfoDialog dialog = new MealInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), meal);
                dialog.setVisible(true);
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

            mealAddFromPreLoadPanel.add(singleMeal);
        }

        JScrollPane scrollPane = new JScrollPane(mealAddFromPreLoadPanel);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "manageMeals");
        });

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
