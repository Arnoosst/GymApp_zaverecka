package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;


/**
 * Panel that allows the user to view and delete their custom-created meals.
 * Each meal is displayed with buttons to show more information or delete it.
 *
 * @author Vojtěch Malínek
 */
public class DeleteCustomMealPanel extends JPanel {
    private User user;
    private UserManager userManager;
    private JPanel parentPanel;
    private CardLayout cardLayout;


    /**
     * Constructs the panel for deleting custom meals.
     *
     * @param user        the current user
     * @param userManager the user manager instance
     * @param cardLayout  the layout manager used for navigation
     * @param parentPanel the parent panel containing this panel
     */
    public DeleteCustomMealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.userManager = userManager;
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        setLayout(new BorderLayout(10, 10));
        initGUI();
    }

    private void initGUI() {
        JPanel mealsPanel = new JPanel();
        mealsPanel.setLayout(new BoxLayout(mealsPanel, BoxLayout.Y_AXIS));

        for (Meal meal : user.getCustomMeals()) {
            JPanel singleMealPanel = new JPanel(new BorderLayout());

            JPanel buttonPanel = new JPanel();
            JButton infoButton = new JButton("More Info");
            JButton deleteButton = new JButton("Delete");

            buttonPanel.add(infoButton);
            buttonPanel.add(deleteButton);
            singleMealPanel.add(buttonPanel, BorderLayout.EAST);

            infoButton.addActionListener(e -> {
                MealInfoFrame mealInfoFrame = new MealInfoFrame(meal);
            });

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this meal?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    user.removeCustomMeal(meal);
                    userManager.saveUsers();
                    refresh();
                    cardLayout.show(parentPanel, "deleteCustomMeal");
                }
            });

            mealsPanel.add(singleMealPanel);
        }

        JScrollPane scrollPane = new JScrollPane(mealsPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "meal");
        });
    }


    /**
     * Refreshes the panel by rebuilding the UI based on the current data.
     */
    public void refresh() {
        removeAll();
        initGUI();
        revalidate();
        repaint();
    }
}
