package GUI.Meal.GUI;

import GUI.MainFrame;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class MealFrame extends JFrame {
    private User user;
    private UserManager userManager;
    private JButton backButton;
    private JButton createCustomMealButton;
    private JButton viewPresetMealsButton;
    private JButton viewCustomMealsButton;
    private JButton deleteCustomMealButton;
    private JButton startLoggingMealButton;



    public MealFrame(User user, UserManager userManager) {
        this.user = user;

        setTitle("Workout Frame");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        initGUI(user, userManager);
        setVisible(true);

    }

    public void initGUI(User user, UserManager userManager) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Meal Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        panel.add(new JLabel("Total number of days u logged: " + user.getMealLogs().size() + " days"));
        panel.add(new JLabel("Total calories burned: " + user.getTotalCaloriesBurned() + " kcal"));


        createCustomMealButton = new JButton("Create Custom Meal");
        viewPresetMealsButton = new JButton("View Preset Meals");
        viewCustomMealsButton = new JButton("View Custom Meals");
        deleteCustomMealButton = new JButton("Delete Custom Meal");
        startLoggingMealButton = new JButton("Start Logging Meal");
        backButton = new JButton("Back");

        panel.add(startLoggingMealButton);
        panel.add(deleteCustomMealButton);
        panel.add(createCustomMealButton);
        panel.add(viewPresetMealsButton);
        panel.add(viewCustomMealsButton);


        panel.add(backButton);

        add(panel, BorderLayout.CENTER);


        startLoggingMealButton.addActionListener(e -> {});




        deleteCustomMealButton.addActionListener(e -> {});






        createCustomMealButton.addActionListener(e -> {});






        viewPresetMealsButton.addActionListener(e -> {
            JFrame preLoadMealsFrame = new JFrame("All Preset Meals");
            preLoadMealsFrame.setSize(400, 300);
            preLoadMealsFrame.setLocationRelativeTo(null);
            preLoadMealsFrame.setLayout(new BorderLayout(10, 10));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            ArrayList<Meal> meals = PreparedMealLoader.loadMealsFromFile("src/data/prepared_meals.txt");
            if (!meals.isEmpty()) {
                for (Meal meal : meals) {
                    textArea.append(meal.getName() + ": " + meal.getKcal() +" kcal" + "\n\n");
                }
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> preLoadMealsFrame.dispose());

            preLoadMealsFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
            preLoadMealsFrame.add(closeButton, BorderLayout.SOUTH);
            preLoadMealsFrame.setVisible(true);});


        viewCustomMealsButton.addActionListener(e -> {
            JFrame mealsFrame = new JFrame("All user meals");
            mealsFrame.setSize(400, 300);
            mealsFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            HashSet<Meal> meals = this.user.getCustomMeals();
            if (meals != null && !meals.isEmpty()) {
                for (Meal meal : meals) {
                    textArea.append(meal.toString() + "\n\n");
                }
            } else {
                textArea.setText("No custom meals.");
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> mealsFrame.dispose());

            mealsFrame.add(new JScrollPane(textArea));
            mealsFrame.add(closeButton, BorderLayout.SOUTH);
            mealsFrame.setVisible(true);
        });

        backButton.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame(user, userManager);
            mainFrame.setVisible(true);
            dispose();
        });

    }




}
