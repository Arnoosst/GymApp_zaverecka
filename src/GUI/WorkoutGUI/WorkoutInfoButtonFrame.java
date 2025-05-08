package GUI.WorkoutGUI;

import Model.Workout;

import javax.swing.*;
import java.awt.*;

class WorkoutInfoButtonFrame extends JFrame {
    private JButton backButton;

    public WorkoutInfoButtonFrame(Workout workout) {
        setTitle("Workout Info - " + workout.getName());
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initGUI(workout);

    }

    public void initGUI(Workout workout) {
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText(workout.toString());

        backButton = new JButton("Back");


        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}

