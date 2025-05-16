package GUI.UserGUI;

import Model.User;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewWorkoutLogsPanel extends JPanel {

    public ViewWorkoutLogsPanel(User user, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        ArrayList<Workout> workouts = user.getWorkoutLogs();
        if (workouts != null && !workouts.isEmpty()) {
            for (Workout workout : workouts) {
                textArea.append(workout.toString() + "\n\n");
            }
        } else {
            textArea.setText("No workouts");
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev -> {
            cardLayout.show(parentPanel, "user");
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }
}

