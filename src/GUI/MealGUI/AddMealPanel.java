package GUI.MealGUI;


import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class AddMealPanel extends JPanel {

    public AddMealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField kcalField = new JTextField();
        JTextField proteinField = new JTextField();
        JTextField carbsField = new JTextField();
        JTextField fatField = new JTextField();

        panel.add(new JLabel("Meal Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Calories (kcal):"));
        panel.add(kcalField);
        panel.add(new JLabel("Protein (g):"));
        panel.add(proteinField);
        panel.add(new JLabel("Carbohydrates (g):"));
        panel.add(carbsField);
        panel.add(new JLabel("Fat (g):"));
        panel.add(fatField);

        JButton backButton = new JButton("Back");
        JButton addButton = new JButton("Add Meal");
        panel.add(backButton);
        panel.add(addButton);

        add(panel, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int kcal = Integer.parseInt(kcalField.getText().trim());
                int protein = Integer.parseInt(proteinField.getText().trim());
                int carbs = Integer.parseInt(carbsField.getText().trim());
                int fat = Integer.parseInt(fatField.getText().trim());

                Meal meal = new Meal(name, kcal, protein, carbs, fat);
                boolean added = user.addMealToLog(meal);

                if (added) {
                    userManager.saveUsers();
                    JOptionPane.showMessageDialog(this, "Meal successfully added!");
                    parentPanel.add(new ManageMealsPanel(user, userManager, cardLayout, parentPanel), "manageMeals");
                    cardLayout.show(parentPanel, "manageMeals");
                } else {
                    JOptionPane.showMessageDialog(this, "Meal could not be added.",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for nutrients.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            parentPanel.add(new ManageMealsPanel(user, userManager, cardLayout, parentPanel), "manageMeals");
            cardLayout.show(parentPanel, "manageMeals");
        });
    }
}

