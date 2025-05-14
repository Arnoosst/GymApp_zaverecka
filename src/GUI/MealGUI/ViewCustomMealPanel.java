package GUI.MealGUI;

import Model.Meal;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class ViewCustomMealPanel extends JPanel {

    public ViewCustomMealPanel(User user, CardLayout cardLayout, JPanel parentPanel){

        JTextArea textArea = new JTextArea();
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
            //TODO klasika dodelat tady vraci zpet do mealframe/panelu
        });

        add(new JScrollPane(textArea));
        add(closeButton, BorderLayout.SOUTH);

    }
}
