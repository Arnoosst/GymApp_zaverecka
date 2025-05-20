package GUI.WorkoutGUI;

import Model.Exercise;
import Model.ExerciseSets;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class WorkoutInfoFrame extends JFrame {
    private Workout workout;
    public WorkoutInfoFrame(Workout workout) {
        this.workout = workout;
        setTitle("Workout Info: " + workout.getName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI() {
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);


        String text = "🏋️ Workout: " + workout.getName() + "\n";
        text += "Exercises:\n";

        for (Exercise exercise : workout.getExercises()) {
            text += "  • " + exercise.getName() + "\n";
            for (ExerciseSets set : exercise.getSets()) {
                text += "      - Reps: " + set.getReps() + ", Weight: " + set.getWeight() + " kg\n";
            }
            text += "\n";
        }

        //TODO neco s tim scrollpane


        infoArea.setText(text);

        JScrollPane scrollPane = new JScrollPane(infoArea);

        JButton backButton = new JButton("Close");
        backButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton,CENTER_ALIGNMENT);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

