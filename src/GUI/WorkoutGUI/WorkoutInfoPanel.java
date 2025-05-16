package GUI.WorkoutGUI;

import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class WorkoutInfoPanel extends JPanel {
    private JButton backButton;

    public WorkoutInfoPanel(Workout workout, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(workout.toString());

        backButton = new JButton("Back");

        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "workoutList");
        });
    }
}

