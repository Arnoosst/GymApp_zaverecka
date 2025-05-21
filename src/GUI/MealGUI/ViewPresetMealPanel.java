package GUI.MealGUI;

import Model.Meal;
import Model.PreparedMealLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * A panel that displays a list of preset meals loaded from a file.
 * The meals are shown in a non-editable text area along with their calorie values.
 *
 * This panel also includes a "Close" button that returns the user to the
 * main meal management screen using CardLayout.
 *
 * @Author: Vojtěch Malínek
 */
public class ViewPresetMealPanel extends JPanel {
    private JTextArea textArea;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    /**
     * Constructs a new ViewPresetMealPanel.
     *
     * @param cardLayout  the layout used for switching between panels
     * @param parentPanel the parent container that holds this panel
     */
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
