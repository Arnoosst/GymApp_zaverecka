package GUI;

import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    public AppFrame(UserManager userManager) {
        setTitle("Fitness App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CardLayout layout = new CardLayout();
        JPanel cards = new JPanel(layout);

        cards.add(new LoginPanel(userManager, layout, cards), "login");

        add(cards);
        layout.show(cards, "login");

        setVisible(true);
    }
}
