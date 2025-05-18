package GUI.MealGUI;

import Model.Meal;

import javax.swing.*;
import java.awt.*;

public class MealInfoFrame extends JFrame {

    public MealInfoFrame(Meal meal) {
        setTitle("Meal Info");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(meal.toString());

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}

