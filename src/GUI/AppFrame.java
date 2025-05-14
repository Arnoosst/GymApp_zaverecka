package GUI;

import GUI.WorkoutGUI.*;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    public AppFrame(UserManager userManager, User user) {
        setTitle("Fitness App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CardLayout layout = new CardLayout();
        JPanel cards = new JPanel(layout);

        cards.add(new LoginPanel(userManager, layout, cards), "login");
        cards.add(new RegisterPanel(userManager, layout, cards), "register");
        cards.add(new MainMenuPanel(user, userManager, layout, cards), "mainMenu");
        cards.add(new WorkoutPanel(user, cards, layout), "workout");
        cards.add(new WorkoutInfoPanel()); // sem musi prijit workout, to je asi spatne co??
        cards.add(new ViewPresetWorkoutsPanel(layout, cards), "viewPresetWorkouts");
        cards.add(new ViewCustomWorkoutPanel(user, layout, cards), "viewCustomWorkouts");
        cards.add(new StartWorkoutPanel(user, layout, cards), "startWorkout");
        cards.add(new SelectWorkoutPanel()); // take musi pridavat workout to je asi blbe??
        cards.add();
        cards.add();
        cards.add();
        cards.add();


        add(cards);
        layout.show(cards, "login");

        setVisible(true);
    }
}
