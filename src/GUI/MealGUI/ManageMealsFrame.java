package GUI.MealGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ManageMealsFrame extends JFrame {

    private User user;
    private UserManager userManager;
    private CaloriesChartMenuFrame caloriesChartMenuFrame;
    private JButton addMealButton;
    private JButton addMealFromPreLoadButton;
    private JButton addMealFromOwnMealsButton;
    private JButton editTodaysMealButton;
    private JButton backButton;

    public ManageMealsFrame(User user, UserManager userManager, CaloriesChartMenuFrame caloriesChartMenuFrame) {
        this.user = user;
        this.userManager = userManager;
        this.caloriesChartMenuFrame = caloriesChartMenuFrame;
        setTitle("Manage Meals");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        initGUI(user, userManager, caloriesChartMenuFrame);
    }

    public void initGUI(User user, UserManager userManager, CaloriesChartMenuFrame caloriesChartMenuFrame) {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        addMealButton = new JButton("Add Meal");
        addMealFromPreLoadButton = new JButton("Add Meal From Pre-Load");
        addMealFromOwnMealsButton = new JButton("Add Meal From Own Meals");
        editTodaysMealButton = new JButton("Edit Todays Meal");
        backButton = new JButton("Back");

        mainPanel.add(addMealButton);
        mainPanel.add(addMealFromPreLoadButton);
        mainPanel.add(addMealFromOwnMealsButton);
        mainPanel.add(editTodaysMealButton);
        mainPanel.add(backButton);
        add(mainPanel, BorderLayout.CENTER);


        addMealButton.addActionListener(e -> {
        });

        addMealFromPreLoadButton.addActionListener(e -> {
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

                infoButton.addActionListener(x -> {
                    MealInfoFrame mealInfoFrame = new MealInfoFrame(meal);
                    mealInfoFrame.setVisible(true);
                });

                selectButton.addActionListener(x -> {
                    boolean added = caloriesChartMenuFrame.addMeal(meal);
                    if (added) {
                        JOptionPane.showMessageDialog(null, "Meal added successfully to today’s log.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Meal could not be added.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                });

            }
        });

        addMealFromOwnMealsButton.addActionListener(e -> {
            JPanel mealAddFromMyOwnMeal = new JPanel();
            mealAddFromMyOwnMeal.setLayout(new BoxLayout(mealAddFromMyOwnMeal, BoxLayout.Y_AXIS));
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

                infoButton.addActionListener(x -> {
                    MealInfoFrame mealInfoFrame = new MealInfoFrame(meal);
                    mealInfoFrame.setVisible(true);
                });

                selectButton.addActionListener(x -> {
                    boolean added = caloriesChartMenuFrame.addMeal(meal);
                    if (added) {
                        JOptionPane.showMessageDialog(null, "Meal added successfully to today’s log.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Meal could not be added.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                });
            }
        });

        editTodaysMealButton.addActionListener(e -> {
            EditMealsFrame editMealsFrame = new EditMealsFrame(user, userManager, caloriesChartMenuFrame);
            editMealsFrame.setVisible(true);
        });

        backButton.addActionListener(e -> {
            CaloriesChartMenuFrame caloriesChartMenuFrame1 = new CaloriesChartMenuFrame(user, userManager);
            caloriesChartMenuFrame1.setVisible(true);
            dispose();
        });
    }
}
