package GUI;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Fitness App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        MainPanel mainPanel = new MainPanel(new MainButtonListener());
        add(mainPanel);
        setVisible(true);
    }


}
