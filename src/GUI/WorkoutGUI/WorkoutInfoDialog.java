package GUI.WorkoutGUI;

import Model.Exercise;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorkoutInfoDialog extends JDialog {

    public WorkoutInfoDialog(JFrame parent, Workout workout) {
        super(parent, "Workout Information", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        StringBuilder info = new StringBuilder();
        info.append("Workout Name: ").append(workout.getName()).append("\n\n");

        List<Exercise> exercises = workout.getExercises();
        if (exercises.isEmpty()) {
            info.append("No exercises in this workout.");
        } else {
            for (Exercise exercise : exercises) {
                info.append("• ").append(exercise.getName())
                        .append(" – ").append(exercise.getSets().length).append(" sets x ");
            }
        }

        infoArea.setText(info.toString());

        JScrollPane scrollPane = new JScrollPane(infoArea);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        getContentPane().setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
