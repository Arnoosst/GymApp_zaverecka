package GUI.WorkoutGUI;

import Model.User;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class ViewCustomWorkoutPanel extends Panel{
    private JButton backButton;

    public ViewCustomWorkoutPanel(User user, CardLayout cardLayout, JPanel parentPanel){
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        HashSet<Workout> workouts = user.getCustomWorkouts();
        if (!workouts.isEmpty()) {
            for (Workout workout : workouts) {
                textArea.append(workout.getName() + ": " + workout.getWorkoutLevel() + "\n\n");
            }
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev ->{
            cardLayout.show(parentPanel, "workout");
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }
}
