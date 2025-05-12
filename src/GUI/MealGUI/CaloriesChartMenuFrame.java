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
    private JButton editTodaysMealButton;
    private User user;
    private UserManager userManager;
    private JButton addMealButton;
    private JButton changeCaloriesGoalButton;
    private LocalDate date;
    private double caloriesGoal;
    private JLabel caloriesLabel;
    private int totalKcal;


    public CaloriesChartMenuFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        user.setCaloriesGoal(user.calculateBMR(user));
        setTitle("Calories Chart");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initGUI(user, userManager);
        setVisible(true);
    }

    public void initGUI(User user, UserManager userManager) {
        setLayout(new BorderLayout(10, 10));
        JLabel title = new JLabel("Calories chart menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(7, 1, 10, 10));

        mainPanel.add(new JLabel("Date: " + LocalDate.now()));
        totalKcal = 0;
        int totalProtein = 0, totalCarbs = 0, totalFat = 0;

        ArrayList<Meal> mealsToday = user.getMealLogs().getOrDefault(LocalDate.now(), new ArrayList<>());

        for (Meal m : mealsToday) {
            totalKcal += m.getKcal();
            totalProtein += m.getProtein();
            totalCarbs += m.getCarbs();
            totalFat += m.getFat();
        }
        mainPanel.add(new JLabel("Nutrients: Protein: " + totalProtein + "g, Carbs: " + totalCarbs + "g, Fat: " + totalFat + "g"));

        caloriesLabel = new JLabel("Calories: " + totalKcal + " / " + user.getCaloriesGoal() + " kcal");
        mainPanel.add(caloriesLabel);

        backButton = new JButton("Back");
        addMealButton = new JButton("Add Meal");
        editTodaysMealButton = new JButton("Edit Todays Meal");
        changeCaloriesGoalButton = new JButton("Change Calories Goal");



        mainPanel.add(addMealButton);
        mainPanel.add(changeCaloriesGoalButton);
        mainPanel.add(editTodaysMealButton);
        mainPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);


        addMealButton.addActionListener(e -> {
            ManageMealsFrame manageMealsFrame = new ManageMealsFrame(user, userManager);
            manageMealsFrame.setVisible(true);
            dispose();
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
            EditMealsFrame editMealsFrame = new EditMealsFrame(user, userManager);
            editMealsFrame.setVisible(true);
            dispose();
        });




        backButton.addActionListener(e -> {
            MealFrame mealFrame = new MealFrame(user, userManager);
            mealFrame.setVisible(true);
            dispose();
        });
        

    }


}
