package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class DeleteCustomMealPanel extends JPanel {

    public DeleteCustomMealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout(10, 10));

        JPanel mealsPanel = new JPanel();
        mealsPanel.setLayout(new BoxLayout(mealsPanel, BoxLayout.Y_AXIS));

        for (Meal meal : user.getCustomMeals()) {
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
                    user.removeCustomMeal(meal);
                    userManager.saveUsers();
                    parentPanel.add(new DeleteCustomMealPanel(user, userManager, cardLayout, parentPanel), "deleteCustomMeal");
                    cardLayout.show(parentPanel, "deleteCustomMeal");
                }
            });

            mealsPanel.add(singleMealPanel);
        }

        JScrollPane scrollPane = new JScrollPane(mealsPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            parentPanel.add(new MealPanel(user, userManager, cardLayout, parentPanel), "mealMenu");
            cardLayout.show(parentPanel, "mealMenu");
        });
    }
}
