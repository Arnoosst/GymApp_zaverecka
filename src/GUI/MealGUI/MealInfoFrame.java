package GUI.MealGUI;

import Model.Meal;

import javax.swing.*;
import java.awt.*;


/**
 * MealInfoFrame is a simple pop-up window that displays detailed information
 * about a selected meal using its toString() method.
 *
 * The window includes a scrollable text area and a back button.
 *
 * @author Vojtěch Malínek
 */
public class MealInfoFrame extends JFrame {

    /**
     * Constructs a new MealInfoFrame that displays information about the given meal.
     *
     * @param meal the meal to be displayed
     */
    public MealInfoFrame(Meal meal) {
        setTitle("Meal Info");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(meal.toString());

        JButton closeButton = new JButton("Back");
        closeButton.addActionListener(e -> dispose());

        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}

