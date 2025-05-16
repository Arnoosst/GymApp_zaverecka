package GUI.MealGUI;


import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditMealsPanel extends JPanel {

    public EditMealsPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout(10, 10));

        JPanel mealEditPanel = new JPanel();
        mealEditPanel.setLayout(new BoxLayout(mealEditPanel, BoxLayout.Y_AXIS));

        ArrayList<Meal> todayMeals = user.getMealLogs().get(LocalDate.now());

        if (todayMeals != null && !todayMeals.isEmpty()) {
            for (Meal meal : new ArrayList<>(todayMeals)) {
                JPanel singleMealPanel = new JPanel(new BorderLayout());
                singleMealPanel.setBorder(BorderFactory.createTitledBorder(meal.getName()));

                JPanel buttonPanel = new JPanel();
                JButton infoButton = new JButton("More Info");
                JButton deleteButton = new JButton("Delete");

                buttonPanel.add(infoButton);
                buttonPanel.add(deleteButton);
                singleMealPanel.add(buttonPanel, BorderLayout.EAST);

                infoButton.addActionListener(e -> {
                    MealInfoDialog dialog = new MealInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), meal);
                    dialog.setVisible(true);
                });

                deleteButton.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this meal?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        todayMeals.remove(meal);
                        userManager.saveUsers();
                        parentPanel.add(new EditMealsPanel(user, userManager, cardLayout, parentPanel), "editMeals");
                        cardLayout.show(parentPanel, "editMeals");
                    }
                });

                mealEditPanel.add(singleMealPanel);
            }
        } else {
            mealEditPanel.add(new JLabel("No meals logged for today."));
        }

        JScrollPane scrollPane = new JScrollPane(mealEditPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            parentPanel.add(new CaloriesChartMenuPanel(user, userManager, cardLayout, parentPanel), "caloriesChart");
            cardLayout.show(parentPanel, "caloriesChart");
        });
    }
}
