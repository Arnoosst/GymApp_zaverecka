package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;


/**
 * A panel that allows the user to add one of their custom meals to today's log.
 *
 * @author Vojtěch Malínek
 */
public class AddMealFromOwnPanel extends JPanel {
    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;


    /**
     * Constructs the panel for selecting meals from the user's custom meals.
     *
     * @param user the current user
     * @param userManager the user manager used to save user data
     * @param cardLayout the layout manager used to switch between panels
     * @param parentPanel the parent panel that holds this panel
     */
    public AddMealFromOwnPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout());
        initGUI();
    }

    private void initGUI(){
        JPanel mealAddFromOwnPanel = new JPanel();
        mealAddFromOwnPanel.setLayout(new BoxLayout(mealAddFromOwnPanel, BoxLayout.Y_AXIS));
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

            mealAddFromOwnPanel.add(singleMeal);
        }

        JScrollPane scrollPane = new JScrollPane(mealAddFromOwnPanel);

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
