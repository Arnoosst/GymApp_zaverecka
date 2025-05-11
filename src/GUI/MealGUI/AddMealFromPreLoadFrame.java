package GUI.MealGUI;

import Model.Meal;
import Model.PreparedMealLoader;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddMealFromPreLoadFrame extends JFrame {
    private User user;
    private UserManager userManager;

    public AddMealFromPreLoadFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        setTitle("Add Meal from Preloaded");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        initGUI(user, userManager);
    }

    private void initGUI(User user, UserManager userManager) {
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
                MealInfoFrame mealInfoFrame = new MealInfoFrame(meal);
                mealInfoFrame.setVisible(true);
            });

            selectButton.addActionListener(e -> {
                boolean added = user.addMeal(meal);
                if (added) {
                    JOptionPane.showMessageDialog(this, "Meal added successfully to today’s log.");
                    user.addMealToLog(meal);
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
            ManageMealsFrame manageMealsFrame = new ManageMealsFrame(user, userManager);
            manageMealsFrame.setVisible(true);
            dispose();
        });


        getContentPane().setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

