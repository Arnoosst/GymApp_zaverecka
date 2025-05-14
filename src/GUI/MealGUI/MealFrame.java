package GUI.MealGUI;

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
        this.userManager = userManager;

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


        startLoggingMealButton.addActionListener(e -> {
            CaloriesChartMenuFrame caloriesChartMenuFrame = new CaloriesChartMenuFrame(user, userManager);
            caloriesChartMenuFrame.setVisible(true);
            dispose();
        });




        deleteCustomMealButton.addActionListener(e -> {
            DeleteCustomMealFrame deleteCustomMealFrame = new DeleteCustomMealFrame(user, userManager);
            deleteCustomMealFrame.setVisible(true);
            dispose();
        });






        createCustomMealButton.addActionListener(e -> {
            CreateCustomMealFrame createCustomMealFrame = new CreateCustomMealFrame(user, userManager);
            createCustomMealFrame.setVisible(true);
            dispose();
        });






        viewPresetMealsButton.addActionListener(e -> {
            ViewPresetMealPanel..
            //zase klasika dodelat to tady
        });


        viewCustomMealsButton.addActionListener(e -> {
            ViewCustomMealPanel..
            //zase klasika dodelat to tady
        });

        backButton.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame(user, userManager);
            mainFrame.setVisible(true);
            dispose();
        });

    }




}
