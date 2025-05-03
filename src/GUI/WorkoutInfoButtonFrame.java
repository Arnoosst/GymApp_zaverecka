package GUI;

import Model.Workout;

import javax.swing.*;
import java.awt.*;

class WorkoutInfoButtonFrame extends JFrame {
    public WorkoutInfoButtonFrame(Workout workout) {
        setTitle("Workout Info - " + workout.getName());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(workout.toString());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}

