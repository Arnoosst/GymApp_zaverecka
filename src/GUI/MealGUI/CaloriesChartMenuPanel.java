package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CaloriesChartMenuPanel extends JPanel {

    private JLabel caloriesLabel;
    private int totalKcal;

    public CaloriesChartMenuPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout(10, 10));
        user.setCaloriesGoal(user.calculateBMR(user));

        JLabel title = new JLabel("Calories chart menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(7, 1, 10, 10));

        mainPanel.add(new JLabel("Date: " + LocalDate.now()));
        totalKcal = 0;
        int totalProtein = 0, totalCarbs = 0, totalFat = 0;

        ArrayList<Meal> mealsToday = user.getMealLogs() != null ? user.getMealLogs().getOrDefault(LocalDate.now(), new ArrayList<>()) : new ArrayList<>();

        for (Meal m : mealsToday) {
            totalKcal += m.getKcal();
            totalProtein += m.getProtein();
            totalCarbs += m.getCarbs();
            totalFat += m.getFat();
        }

        mainPanel.add(new JLabel("Nutrients: Protein: " + totalProtein + "g, Carbs: " + totalCarbs + "g, Fat: " + totalFat + "g"));

        caloriesLabel = new JLabel("Calories: " + totalKcal + " / " + user.getCaloriesGoal() + " kcal");
        mainPanel.add(caloriesLabel);

        JButton addMealButton = new JButton("Add Meal");
        JButton changeCaloriesGoalButton = new JButton("Change Calories Goal");
        JButton editTodaysMealButton = new JButton("Edit Todays Meal");
        JButton backButton = new JButton("Back");

        mainPanel.add(addMealButton);
        mainPanel.add(changeCaloriesGoalButton);
        mainPanel.add(editTodaysMealButton);
        mainPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);

        addMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "manageMeals");
        });

        changeCaloriesGoalButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter new calorie goal:", (int) user.getCaloriesGoal());
            if (input != null) {
                try {
                    int newGoal = Integer.parseInt(input);
                    if (newGoal > 0) {
                        user.setCaloriesGoal(newGoal);
                        userManager.saveUsers();
                        caloriesLabel.setText("Calories: " + totalKcal + " / " + user.getCaloriesGoal() + " kcal");
                        JOptionPane.showMessageDialog(this, "Calorie goal updated to " + newGoal + " kcal.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Please enter a positive number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editTodaysMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "editMeals");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "meal");
        });
    }
}