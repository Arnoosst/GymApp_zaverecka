package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * This panel displays all custom meals created by the user.
 *
 * The panel includes a close button to return to the main meal screen,
 * and a refresh() method to reload the displayed data.
 *
 * @author Vojtěch Malínek
 */
public class ViewCustomMealPanel extends JPanel {
    private JTextArea textArea;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    /**
     * Constructs a panel that displays the user's custom meals.
     *
     * @param user         the user whose meals are displayed
     * @param cardLayout   layout used for switching between panels
     * @param parentPanel  parent container with CardLayout
     */
    public ViewCustomMealPanel(User user, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        initGUI();
    }

    private void initGUI() {
        textArea = new JTextArea();
        textArea.setEditable(false);

        String text = "";

        HashSet<Meal> meals = user.getCustomMeals();
        if (meals != null && !meals.isEmpty()) {
            for (Meal meal : meals) {
                text += meal.toString() + "\n\n";
            }
        } else {
            text = "No custom meals.";
        }

        textArea.setText(text);


        JButton closeButton = new JButton("Back");
        closeButton.addActionListener(ev -> {
            cardLayout.show(parentPanel, "meal");
        });

        add(new JScrollPane(textArea));
        add(closeButton, BorderLayout.SOUTH);

    }

    /**
     * Refreshes the panel content by reloading the user's custom meals.
     */
    public void refresh() {
        textArea.setText("");

        HashSet<Meal> meals = user.getCustomMeals();
        if (meals != null && !meals.isEmpty()) {
            for (Meal meal : meals) {
                textArea.append(meal.toString() + "\n\n");
            }
        } else {
            textArea.setText("No custom meals.");
        }
    }
}
