package GUI.MealGUI;

import Model.Meal;
import Model.PreparedMealLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewPresetMealPanel extends JPanel {

    public ViewPresetMealPanel(CardLayout cardLayout, JPanel parentPanel){
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        ArrayList<Meal> meals = PreparedMealLoader.loadMealsFromFile("src/data/prepared_meals.txt");
        if (!meals.isEmpty()) {
            for (Meal meal : meals) {
                textArea.append(meal.getName() + ": " + meal.getKcal() +" kcal" + "\n\n");
            }
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev ->{
            cardLayout.show(parentPanel, "meal");
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

    }
}
