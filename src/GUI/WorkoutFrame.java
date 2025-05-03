package GUI;

import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class WorkoutFrame extends JFrame {

    private User user;
    private UserManager userManager;
    private JButton startWorkoutButton;
    private JButton createCustomWorkoutButton;
    private JButton viewPresetWorkoutsButton;
    private JButton viewCustomWorkoutsButton;
    private JButton deleteCustomWorkoutButton;
    private JButton backButton;

    public WorkoutFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;

        setTitle("Workout Frame");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initGUI();
        setVisible(true);
    }

    private void initGUI() {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Workout sekce", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(9, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Statistiky"));
        panel.add(new JLabel("Celkový počet tréninků: " + user.getWorkoutLogs().size()));
        panel.add(new JLabel("Celkový objem (volume) zvednuté váhy: "+user.getTotalVolumeLiftedFromLogWorkout()+" kg"));
        panel.add(new JLabel("Celkový pocet kcal spalenych: "+user.getKcalBurned()+" kcal"));

        startWorkoutButton = new JButton("Spustit trénink");
        createCustomWorkoutButton = new JButton("Vytvořit vlastní trénink");
        viewPresetWorkoutsButton = new JButton("Prohlédnout předem připravené tréninky");
        viewCustomWorkoutsButton = new JButton("Prohlédnout vlastní tréninky");
        deleteCustomWorkoutButton = new JButton("Smazat vlastní trénink");
        backButton = new JButton("Zpět");

        panel.add(startWorkoutButton);
        panel.add(createCustomWorkoutButton);
        panel.add(viewPresetWorkoutsButton);
        panel.add(viewCustomWorkoutsButton);
        panel.add(deleteCustomWorkoutButton);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);


        startWorkoutButton.addActionListener(e -> {
            // TODO otevřít okno pro spuštění tréninku a logování výkonu
        });

        createCustomWorkoutButton.addActionListener(e -> {
            // TODO otevřít okno pro vytvoření vlastního tréninku
        });

        viewPresetWorkoutsButton.addActionListener(e -> {
            // TODO zobrazit seznam předem připravených tréninků
        });

        viewCustomWorkoutsButton.addActionListener(e -> {
            // TODO zobrazit seznam vlastních tréninků
        });

        deleteCustomWorkoutButton.addActionListener(e -> {
            // TODO umožnit uživateli smazat vlastní trénink
        });

        backButton.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame(user, userManager);
            mainFrame.setVisible(true);
            dispose();
        });
    }
}

