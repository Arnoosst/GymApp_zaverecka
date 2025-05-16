package GUI.MealGUI;

import Model.Meal;

import javax.swing.*;
import java.awt.*;

public class MealInfoDialog extends JDialog {
    public MealInfoDialog(JFrame parent, Meal meal) {
        super(parent, "Meal Information", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(
                "Name: " + meal.getName() + "\n" +
                        "Calories: " + meal.getKcal() + "\n" +
                        "Protein: " + meal.getProtein() + "g\n" +
                        "Carbs: " + meal.getCarbs() + "g\n" +
                        "Fat: " + meal.getFat() + "g\n"
        );

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(infoArea, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);
    }
}
