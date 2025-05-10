package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CaloriesChartMenuFrame extends JFrame {
    private JButton backButton;
    private User user;
    private UserManager userManager;
    private JButton addMealButton;
    private JButton changeCaloriesGoalButton;
    private LocalDate date;
    private int caloriesGoal;


    public CaloriesChartMenuFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        setTitle("Calories Chart");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        initGUI(user, userManager);
    }

    public void initGUI(User user, UserManager userManager) {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        mainPanel.add(new JLabel("Date: " + LocalDate.now()));
        int totalKcal = 0, totalProtein = 0, totalCarbs = 0, totalFat = 0;
        for (Meal m : user.getMealsToday()) {
            totalKcal += m.getKcal();
            totalProtein += m.getProtein();
            totalCarbs += m.getCarbs();
            totalFat += m.getFat();
        }
        mainPanel.add(new JLabel("Nutrients: Protein: " + totalProtein + "g, Carbs: " + totalCarbs + "g, Fat: " + totalFat + "g"));
        mainPanel.add(new JLabel("Calories: " + totalKcal + " / " + user.calculateBMR(user) + " kcal"));
        add(mainPanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        backButton = new JButton("Back");
        addMealButton = new JButton("Add Meal");
        changeCaloriesGoalButton = new JButton("Change Calories Goal");


        buttonPanel.add(addMealButton);
        buttonPanel.add(changeCaloriesGoalButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.CENTER);


        addMealButton.addActionListener(e -> {
            ManageMealsFrame manageMealsFrame = new ManageMealsFrame(user, userManager);
            manageMealsFrame.setVisible(true);
            dispose();
        });


        changeCaloriesGoalButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(null, "Enter new calorie goal:", "Change Calorie Goal", JOptionPane.PLAIN_MESSAGE);

            if (input != null) {
                try {
                    int newGoal = Integer.parseInt(input);
                    if (newGoal > 0) {
                        setCaloriesGoal(newGoal);
                        JOptionPane.showMessageDialog(null, "Calorie goal updated to " + newGoal + " kcal.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Calorie goal must be a positive number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });




        backButton.addActionListener(e -> {
            MealFrame mealFrame = new MealFrame(user, userManager);
            mealFrame.setVisible(true);
            dispose();
        });
        

    }



    public int getCaloriesGoal() {
        return caloriesGoal;
    }

    public void setCaloriesGoal(int caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
    }


}
