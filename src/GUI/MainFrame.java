package GUI;

import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton userProfileButton;
    private JButton workoutButton;
    private JButton mealButton;
    private User user;
    private UserManager userManager;


    public MainFrame(User user, UserManager userManager) {
        this.user = user;
        setTitle("Fitness App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI(user, userManager);
    }

    private void initUI(User user, UserManager userManager) {

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        userProfileButton = new JButton("User");
        workoutButton = new JButton("Workout");
        mealButton = new JButton("Kalorie");

        panel.add(userProfileButton);
        panel.add(workoutButton);
        panel.add(mealButton);


        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        add(panel);
        setVisible(true);


        userProfileButton.addActionListener(e -> {
            getContentPane().removeAll();
            add(new UserFrame(user, userManager));
            revalidate();
            repaint();
        });
    }


}
