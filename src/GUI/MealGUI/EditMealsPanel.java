package GUI.MealGUI;


import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;



/**
 * EditMealsPanel allows users to view and manage the meals they have logged for the current day.
 * It displays a list of meals with options to view details or delete them.
 *
 * @author Vojtěch Malínek
 */
public class EditMealsPanel extends JPanel {

    private UserManager userManager;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private CaloriesChartMenuPanel caloriesChartMenuPanel;


    /**
     * Constructs the EditMealsPanel.
     *
     * @param user        the currently logged-in user
     * @param userManager the user manager instance
     * @param cardLayout  the CardLayout used for switching views
     * @param parentPanel the panel container using CardLayout
     */
    public EditMealsPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        this.userManager = userManager;
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.caloriesChartMenuPanel = caloriesChartMenuPanel;
        setLayout(new BorderLayout(10, 10));

        initGUI();
    }

    private void initGUI(){
        JPanel mealEditPanel = new JPanel();
        mealEditPanel.setLayout(new BoxLayout(mealEditPanel, BoxLayout.Y_AXIS));

        ArrayList<Meal> todayMeals;
        if (user.getMealLogs() == null || user.getMealLogs().isEmpty()) {
            todayMeals = new ArrayList<>();
        } else {
            todayMeals = user.getMealLogs().get(LocalDate.now());
        }


        if (todayMeals != null && !todayMeals.isEmpty()) {
            for (Meal meal : new ArrayList<>(todayMeals)) {
                JPanel singleMealPanel = new JPanel(new BorderLayout());
                singleMealPanel.setBorder(BorderFactory.createTitledBorder(meal.getName()));

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
                        todayMeals.remove(meal);
                        userManager.saveUsers();
                        refresh();
                        cardLayout.show(parentPanel, "editMeals");
                    }
                });

                mealEditPanel.add(singleMealPanel);
            }
        } else {
            mealEditPanel.add(new JLabel("No meals logged for today."));
        }

        JScrollPane scrollPane = new JScrollPane(mealEditPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            caloriesChartMenuPanel.refresh();
            cardLayout.show(parentPanel, "caloriesChartMenu");
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

    public void setCaloriesChartMenuPanel(CaloriesChartMenuPanel caloriesChartMenuPanel) {
        this.caloriesChartMenuPanel = caloriesChartMenuPanel;
    }
}
