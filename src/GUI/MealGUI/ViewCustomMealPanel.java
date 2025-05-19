package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class ViewCustomMealPanel extends JPanel {
    private JTextArea textArea;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;

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

        HashSet<Meal> meals = user.getCustomMeals();
        if (meals != null && !meals.isEmpty()) {
            for (Meal meal : meals) {
                textArea.append(meal.toString() + "\n\n");
            }
        } else {
            textArea.setText("No custom meals.");
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev -> {
            cardLayout.show(parentPanel, "meal");
        });

        add(new JScrollPane(textArea));
        add(closeButton, BorderLayout.SOUTH);

    }

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
