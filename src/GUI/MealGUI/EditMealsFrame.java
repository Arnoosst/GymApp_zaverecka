package GUI.MealGUI;

import GUI.WorkoutGUI.DeleteCustomWokoutButtonFrame;
import GUI.WorkoutGUI.WorkoutFrame;
import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class EditMealsFrame extends JFrame{
    private User user;
    private UserManager userManager;
    private JButton infoButton;
    private JButton deleteButton;
    private JButton backButton;


    public EditMealsFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;


        setLayout(new BorderLayout(10, 10));
        setTitle("User: " + user.getUserName() + " - Fitness App -");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(user, userManager);
    }

    private void initGUI(User user, UserManager userManager) {
        JPanel mealEditPanel = new JPanel();
        mealEditPanel.setLayout(new BoxLayout(mealEditPanel, BoxLayout.Y_AXIS));

        for (Meal meal : user.getMealsToday()) {
            JPanel singleMealButton = new JPanel(new BorderLayout());
            singleMealButton.setBorder(BorderFactory.createTitledBorder(meal.getName()));

            JPanel buttonPanel = new JPanel();
            JButton infoButton = new JButton("More Info");
            JButton deleteButton = new JButton("Delete");

            buttonPanel.add(infoButton);
            buttonPanel.add(deleteButton);
            singleMealButton.add(buttonPanel, BorderLayout.EAST);

            infoButton.addActionListener(e -> {
                MealInfoFrame mealInfoFrame = new MealInfoFrame(meal);
                mealInfoFrame.setVisible(true);
            });

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this meal?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    user.getMealsToday().remove(meal);
                    EditMealsFrame editMealsFrame = new EditMealsFrame(user, userManager);
                    editMealsFrame.setVisible(true);
                    dispose();
                }
            });

            mealEditPanel.add(singleMealButton);
        }

        JScrollPane scrollPane = new JScrollPane(mealEditPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            ManageMealsFrame manageMealsFrame = new ManageMealsFrame(user, userManager);
            manageMealsFrame.setVisible(true);
            dispose();
        });
    }
}
