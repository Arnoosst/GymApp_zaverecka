package GUI.MealGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ManageMealsFrame extends JFrame {

    private User user;
    private UserManager userManager;
    private JButton addMealButton;
    private JButton addMealFromPreLoadButton;
    private JButton addMealFromOwnMealsButton;
    private JButton editTodaysMealButton;
    private JButton backButton;

    public ManageMealsFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;

        setTitle("Manage Meals");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        initGUI(user, userManager);
    }

    public void initGUI(User user, UserManager userManager) {
        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));

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
            AddMealFrame addMealFrame = new AddMealFrame(user, userManager);
            addMealFrame.setVisible(true);
            dispose();
        });

        addMealFromPreLoadButton.addActionListener(e -> {
            AddMealFromPreLoadFrame addMealFromPreLoadFrame = new AddMealFromPreLoadFrame(user, userManager);
            addMealFromPreLoadFrame.setVisible(true);
            dispose();
        });

        addMealFromOwnMealsButton.addActionListener(e -> {
            AddMealFromOwnFrame addMealFromOwnFrame = new AddMealFromOwnFrame(user, userManager);
            addMealFromOwnFrame.setVisible(true);
            dispose();
        });


        editTodaysMealButton.addActionListener(e -> {
            EditMealsFrame editMealsFrame = new EditMealsFrame(user, userManager);
            editMealsFrame.setVisible(true);
            dispose();
        });

        backButton.addActionListener(e -> {
            CaloriesChartMenuFrame caloriesChartMenuFrame1 = new CaloriesChartMenuFrame(user, userManager);
            caloriesChartMenuFrame1.setVisible(true);
            dispose();
        });
    }
}
