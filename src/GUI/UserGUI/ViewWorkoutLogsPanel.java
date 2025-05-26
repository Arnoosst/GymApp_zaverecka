package GUI.UserGUI;

import Model.User;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Panel for displaying the user's workout logs.
 *
 * Shows a scrollable list of completed workouts.
 * Includes a button to return back to the main user panel.
 *
 * @author Vojtěch
 */
public class ViewWorkoutLogsPanel extends JPanel {
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    /**
     * Constructs a panel to display user's workout history.
     *
     * @param user the currently logged-in user
     * @param cardLayout the layout manager for switching panels
     * @param parentPanel the parent panel that contains this view
     */
    public ViewWorkoutLogsPanel(User user, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        setLayout(new BorderLayout());
        initGUI();
    }

    private void initGUI() {
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

        JButton closeButton = new JButton("Back");
        closeButton.addActionListener(ev -> {
            cardLayout.show(parentPanel, "user");
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }


    /**
     * Refreshes the workout log list by reinitializing the GUI.
     * Call this if a new workout log is added.
     */
    public void refresh() {
        removeAll();
        initGUI();
        revalidate();
        repaint();
    }
}

