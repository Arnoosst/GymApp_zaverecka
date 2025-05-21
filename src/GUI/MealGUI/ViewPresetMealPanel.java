package GUI.MealGUI;

import Model.Meal;
import Model.PreparedMealLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewPresetMealPanel extends JPanel {
    private JTextArea textArea;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    public ViewPresetMealPanel(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        initGUI();
    }

    private void initGUI() {
        textArea = new JTextArea();
        textArea.setEditable(false);

        String text = "";

        ArrayList<Meal> meals = PreparedMealLoader.loadMealsFromFile("src/data/prepared_meals.txt");
        if (!meals.isEmpty()) {
            for (Meal meal : meals) {
                text += meal.getName() + ": " + meal.getKcal() + " kcal\n\n";
            }
        } else {
            text = "No meals found.\n";
        }

        textArea.setText(text);


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev ->{
            cardLayout.show(parentPanel, "meal");
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

    }
}
