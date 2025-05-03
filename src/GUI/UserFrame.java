package GUI;

import Model.Meal;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class UserFrame extends JFrame {
    private User user;
    private UserManager userManager;
    private JButton bmrButtonButton;
    private JButton showMealLogsButton;
    private JButton showWorkoutLogsButton;
    private JButton changeDataButtonButton;
    private JButton showUsersMealsButton;
    private JButton showUsersWorkoutsButton;
    private JButton backButtonButton;

    public UserFrame(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;

        setLayout(new BorderLayout(10, 10));
        setTitle("User: " + user.getUserName() + " - Fitness App -");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(user, userManager);
    }


    public void initGUI(User user, UserManager userManager) {
        bmrButtonButton = new JButton("Ukázat BMR");
        showMealLogsButton = new JButton("Zobrazit kalorické tabulky (7 dní)");
        showWorkoutLogsButton = new JButton("Zobrazit tréninkové logy (7 dní)");
        changeDataButtonButton = new JButton("Změna údajů");
        showUsersMealsButton = new JButton("Zobrazit vlastni jidla");
        showUsersWorkoutsButton = new JButton("Zobrazit vlastni treninky");
        backButtonButton = new JButton("zpet");


        JLabel title = new JLabel("Informace o uživateli", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);


        JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Informace o uživateli"));
        panel.add(new JLabel("Uživatelské jméno: " + this.user.getUserName()));
        panel.add(new JLabel("Jméno: " + this.user.getName()));
        panel.add(new JLabel("Věk: " + this.user.getAge() + " let"));
        panel.add(new JLabel("Výška: " + this.user.getHeight() + " cm"));
        panel.add(new JLabel("Váha: " + this.user.getWeight() + " kg"));
        panel.add(new JLabel("Pohlaví: " + this.user.getGender()));
        panel.add(new JLabel("Záznamy o kaloriích: " + this.user.getMealLogs().size() + " dní"));
        panel.add(new JLabel("Záznamy o tréninku: "+ this.user.getWorkoutLogs().size() +" dní"));
        panel.add(bmrButtonButton);



        JPanel panel2 = new JPanel(new GridLayout(6, 1, 5, 5));
        panel2.setBorder(BorderFactory.createTitledBorder("Akce"));
        panel2.add(changeDataButtonButton);
        panel2.add(showMealLogsButton);
        panel2.add(showWorkoutLogsButton);
        panel2.add(showUsersMealsButton);
        panel2.add(showUsersWorkoutsButton);
        panel2.add(backButtonButton);


        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.add(panel);
        centerPanel.add(panel2);


        bmrButtonButton.addActionListener(e -> {
            double bmr = this.user.calculateBMR(this.user);
            JOptionPane.showMessageDialog(this, "Tvůj BMR je: " + bmr, "BMR Výpočet", JOptionPane.INFORMATION_MESSAGE);
        });
        showMealLogsButton.addActionListener(e -> {
            JFrame mealFrame = new JFrame("Posledních 7 jídel");
            mealFrame.setSize(400, 300);
            mealFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            ArrayList<Meal> meals = this.user.get7daysOldMeal();
            if (meals != null && !meals.isEmpty()) {
                for (Meal meal : meals) {
                    textArea.append(meal.toString() + "\n\n");
                }
            } else {
                textArea.setText("Žádná jídla za posledních 7 dní.");
            }

            mealFrame.add(new JScrollPane(textArea));
            mealFrame.setVisible(true);
        });
        showWorkoutLogsButton.addActionListener(e -> {
            JFrame mealFrame = new JFrame("Posledních 7 workout");
            mealFrame.setSize(400, 300);
            mealFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            ArrayList<Workout> workouts = this.user.get7daysOldWorkout();
            if (workouts != null && !workouts.isEmpty()) {
                for (Workout workout : workouts) {
                    textArea.append(workout.toString() + "\n\n");
                }
            } else {
                textArea.setText("Žádny workout za posledních 7 dní.");
            }

            mealFrame.add(new JScrollPane(textArea));
            mealFrame.setVisible(true);
        });

        changeDataButtonButton.addActionListener(e -> {
            new ChangeUserDataFrame(this.user, this.userManager);
            dispose();
        });
        showUsersMealsButton.addActionListener(e -> {
            JFrame mealsFrame = new JFrame("Všechna jídla uživatele");
            mealsFrame.setSize(400, 300);
            mealsFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            HashSet<Meal> meals = this.user.getCustomMeals();
            if (meals != null && !meals.isEmpty()) {
                for (Meal meal : meals) {
                    textArea.append(meal.toString() + "\n\n");
                }
            } else {
                textArea.setText("Žádná vlastní jídla.");
            }

            mealsFrame.add(new JScrollPane(textArea));
            mealsFrame.setVisible(true);
        });
        showUsersWorkoutsButton.addActionListener(e -> {
            JFrame mealsFrame = new JFrame("Všechna cviceni uživatele");
            mealsFrame.setSize(400, 300);
            mealsFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            HashSet<Workout> workouts = this.user.getCustomWorkouts();
            if (workouts != null && !workouts.isEmpty()) {
                for (Workout workout : workouts) {
                    textArea.append(workout.toString() + "\n\n");
                }
            } else {
                textArea.setText("Uživatel zatím nemá žádná cviceni.");
            }

            mealsFrame.add(new JScrollPane(textArea));
            mealsFrame.setVisible(true);
        });


        backButtonButton.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame(this.user, this.userManager);
            mainFrame.setVisible(true);
            dispose();
        });



        add(centerPanel, BorderLayout.CENTER);
    }

}