package GUI.MealGUI;


import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that allows the user to add a meal to their daily log.
 * The user can input the meal's name, calories, protein, carbohydrates, and fat.
 * After successful input, the meal is saved and the user is navigated back to the meal management screen.
 *
 * @author Vojtěch Malínek
 */
public class AddMealPanel extends JPanel {
    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;


    /**
     * Constructs the AddMealPanel with required data.
     *
     * @param user the current user
     * @param userManager the user manager used to save user data
     * @param cardLayout the layout controller for switching panels
     * @param parentPanel the parent panel that uses CardLayout
     */
    public AddMealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout());
        initGUI();
    }
    private void initGUI() {
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
            cardLayout.show(parentPanel, "manageMeals");
        });
    }
}

