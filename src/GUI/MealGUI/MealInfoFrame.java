package GUI.MealGUI;

import Model.Meal;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class MealInfoFrame extends JFrame {
    private JButton backButton;

    private Meal meal;

    public MealInfoFrame(Meal meal) {
        this.meal = meal;

        setTitle("Meal Info - " + meal.getName());
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initGUI(meal);

    }

    public void initGUI(Meal meal) {
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(meal.toString());

        backButton = new JButton("Back");


        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}
