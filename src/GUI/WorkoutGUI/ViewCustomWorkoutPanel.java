package GUI.WorkoutGUI;

import Model.User;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class ViewCustomWorkoutPanel extends JPanel {

    private JTextArea textArea;
    private User user;

    public ViewCustomWorkoutPanel(User user, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;

        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev -> {
            cardLayout.show(parentPanel, "workout");
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        refresh();
    }

    public void refresh() {
        textArea.setText("");

        HashSet<Workout> workouts = user.getCustomWorkouts();
        if (!workouts.isEmpty()) {
            for (Workout workout : workouts) {
                textArea.append(workout.getName() + ": " + workout.getWorkoutLevel() + "\n\n");
            }
        } else {
            textArea.append("No workouts found.\n");
        }
    }
}
