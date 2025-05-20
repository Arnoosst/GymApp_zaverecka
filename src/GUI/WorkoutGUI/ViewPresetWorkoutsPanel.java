package GUI.WorkoutGUI;

import Model.PreparedWorkoutLoader;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewPresetWorkoutsPanel extends JPanel {
    private JButton backButton;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public ViewPresetWorkoutsPanel(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        initGUI();
    }

    private void initGUI() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        String text = "";

        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts("src/data/prepared_workouts.txt");
        if (!workouts.isEmpty()) {
            for (Workout workout : workouts) {
                text += workout.getName() + ": " + workout.getWorkoutLevel() + "\n\n";
            }
        } else {
            text = "No workouts found.\n";
        }

        textArea.setText(text);


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev ->{
            cardLayout.show(parentPanel, "workout");
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

}
