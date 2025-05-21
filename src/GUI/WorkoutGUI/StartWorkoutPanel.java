package GUI.WorkoutGUI;

import Model.User;
import Model.UserManager;

import javax.swing.*;
        import java.awt.*;



/**
 * Panel for starting a workout session.
 * Allows the user to choose between their own workouts or pre-set workouts,
 * or return back to the previous menu.
 *
 * @author Vojtěch
 */
public class StartWorkoutPanel extends JPanel {
    private SelectUserWorkoutPanel selectUserWorkoutPanel;
    private UserManager userManager;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;


    /**
     * Constructs the panel for starting a workout.
     *
     * @param user the user starting the workout
     * @param cardLayout the CardLayout used for switching between views
     * @param parentPanel the parent panel using the CardLayout
     * @param selectUserWorkoutPanel the panel used to display the user's own workouts
     */
    public StartWorkoutPanel(User user, CardLayout cardLayout, JPanel parentPanel, SelectUserWorkoutPanel selectUserWorkoutPanel) {
        this.selectUserWorkoutPanel = selectUserWorkoutPanel;
        this.userManager = userManager;
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        initGUI();
    }


    /**
     * Initializes the GUI components of the panel, including:
     * - a button for user's own workouts
     * - a button for pre-set workouts
     * - a back button
     * Adds listeners to switch panels accordingly.
     */
    private void initGUI() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton ownWorkout = new JButton("My own workouts");
        JButton preSetWorkout = new JButton("Pre set workouts");
        JButton backButton = new JButton("Back");

        panel.add(ownWorkout);
        panel.add(preSetWorkout);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);


        ownWorkout.addActionListener(e -> {
            selectUserWorkoutPanel.refresh();
            cardLayout.show(parentPanel, "selectUserWorkout");
        });

        preSetWorkout.addActionListener(e -> {
            cardLayout.show(parentPanel, "selectPreLoadWorkout");
        });

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "workout");
        });
    }
}
