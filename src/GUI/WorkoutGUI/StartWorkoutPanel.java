package GUI.WorkoutGUI;

import Model.User;
import Model.UserManager;

import javax.swing.*;
        import java.awt.*;

public class StartWorkoutPanel extends JPanel {
    private SelectUserWorkoutPanel selectUserWorkoutPanel;
    private UserManager userManager;
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public StartWorkoutPanel(User user, CardLayout cardLayout, JPanel parentPanel, SelectUserWorkoutPanel selectUserWorkoutPanel) {
        this.selectUserWorkoutPanel = selectUserWorkoutPanel;
        this.userManager = userManager;
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        initGUI();
    }

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
