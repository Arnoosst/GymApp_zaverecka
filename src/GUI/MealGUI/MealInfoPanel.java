package GUI.MealGUI;

import Model.Meal;

import javax.swing.*;
import java.awt.*;

public class MealInfoPanel extends JPanel {

    public MealInfoPanel(Meal meal, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(meal.toString());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.previous(parentPanel));

        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
