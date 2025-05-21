package GUI.MealGUI;

import GUI.WorkoutGUI.WorkoutPanel;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddMealFromPreLoadPanel extends JPanel {
    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private ArrayList<Meal> meals;


    public AddMealFromPreLoadPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        this.meals = PreparedMealLoader.loadMealsFromFile("src/data/prepared_meals.txt");

        setLayout(new BorderLayout());
        initGUI();
    }

    private void initGUI(){
        JPanel mealAddFromPreLoadPanel = new JPanel();
        mealAddFromPreLoadPanel.setLayout(new BoxLayout(mealAddFromPreLoadPanel, BoxLayout.Y_AXIS));

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
                MealInfoFrame mealInfoFrame = new MealInfoFrame(meal);
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
