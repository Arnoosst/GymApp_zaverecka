package GUI.WorkoutGUI;

import Model.PreparedWorkoutLoader;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Panel displaying a list of preset workouts loaded from a file.
 *
 * @author Vojtěch
 */
public class ViewPresetWorkoutsPanel extends JPanel {
    private JButton backButton;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private ArrayList<Workout> workouts;


    /**
     * Constructs the panel to view preset workouts.
     *
     * @param cardLayout the CardLayout used to switch panels
     * @param parentPanel the parent container that uses CardLayout
     */
    public ViewPresetWorkoutsPanel(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.workouts = PreparedWorkoutLoader.loadPreparedWorkouts("src/data/prepared_workouts.txt");

        setLayout(new BorderLayout());
        initGUI();
    }


    /**
     * Initializes the GUI components and layout of this panel.
     * Displays the list of workouts or a message if no workouts are found.
     */
    private void initGUI() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        String text = "";

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
