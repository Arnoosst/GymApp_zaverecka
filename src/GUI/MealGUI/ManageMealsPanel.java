package GUI.MealGUI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class ManageMealsPanel extends JPanel {
    private CaloriesChartMenuPanel caloriesChartMenuPanel;
    private UserManager userManager;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    public ManageMealsPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel, CaloriesChartMenuPanel caloriesChartMenuPanel) {
        this.caloriesChartMenuPanel = caloriesChartMenuPanel;
        this.userManager = userManager;
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        initGUI();
    }
    private void initGUI() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addMealButton = new JButton("Add Meal");
        JButton addMealFromPreLoadButton = new JButton("Add Meal From Pre-Load");
        JButton addMealFromOwnMealsButton = new JButton("Add Meal From Own Meals");
        JButton backButton = new JButton("Back");

        mainPanel.add(addMealButton);
        mainPanel.add(addMealFromPreLoadButton);
        mainPanel.add(addMealFromOwnMealsButton);
        mainPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);

        addMealButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "addMeal");
        });

        addMealFromPreLoadButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "addMealFromPreLoad");
        });

        addMealFromOwnMealsButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "addMealFromOwn");
        });

        backButton.addActionListener(e -> {
            caloriesChartMenuPanel.refresh();
            cardLayout.show(parentPanel, "caloriesChartMenu");
        });
    }
}
