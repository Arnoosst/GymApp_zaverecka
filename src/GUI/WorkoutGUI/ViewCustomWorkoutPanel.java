package GUI.WorkoutGUI;

import Model.User;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;


/**
 * Panel for viewing the user's custom workouts.
 * Displays a list of custom workouts or a message if none exist.
 * Allows returning to the main workout panel.
 *
 * @author Vojtěch
 */
public class ViewCustomWorkoutPanel extends JPanel {

    private JTextArea textArea;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    /**
     * Constructs the panel for viewing custom workouts.
     *
     * @param user the user whose workouts are being displayed
     * @param cardLayout the CardLayout used to switch panels
     * @param parentPanel the parent container that uses CardLayout
     */
    public ViewCustomWorkoutPanel(User user, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

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



    /**
     * Refreshes the text area to display the latest custom workouts.
     * If no workouts are found, displays a corresponding message.
     */
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
