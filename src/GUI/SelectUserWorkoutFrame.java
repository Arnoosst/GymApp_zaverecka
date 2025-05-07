package GUI;

import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class SelectUserWorkoutFrame extends JFrame {
    private User user;
    private UserManager userManager;
    private JButton infoButton;
    private JButton selectButton;
    private JButton backButton;

    public SelectUserWorkoutFrame(User user, UserManager userManager) {
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
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));


        for (Workout workout : user.getCustomWorkouts()) {
            JPanel singleWorkoutPanel = new JPanel(new BorderLayout());
            singleWorkoutPanel.setBorder(BorderFactory.createTitledBorder(workout.getName()));

            JPanel buttonPanel = new JPanel();
            JButton infoButton = new JButton("More Info");
            JButton selectButton = new JButton("Select");

            buttonPanel.add(infoButton);
            buttonPanel.add(selectButton);
            singleWorkoutPanel.add(buttonPanel, BorderLayout.EAST);

            infoButton.addActionListener(e -> {
                WorkoutInfoButtonFrame workoutInfoButtonFrame = new WorkoutInfoButtonFrame(workout);
                workoutInfoButtonFrame.setVisible(true);
            });

            selectButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to start this workout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JTextField repsField;
                    JTextField weightField;
                    JTextField setsField;
                    for(int i = 0; i < workout.getExercises().size(); i ++ ){
                        JPanel panelForSetCount = new JPanel(new GridLayout(5, 2, 10, 5));
                        repsField = new JTextField();
                        weightField = new JTextField();
                        backButton = new JButton("Back");
                        setsField = new JTextField();
                        int setCount = 0;
                        panelForSetCount.add(new JLabel("Set how many sets you will be doing in first exercise:"));
                        panelForSetCount.add(setsField);

                        setCount = Integer.parseInt(setsField.getText().trim());
                        for(int j = 0; j < setCount; j++){
                            JPanel panelForInput = new JPanel(new GridLayout(4, 2, 5, 5));
                            panelForInput.add(new JLabel("Reps:"));
                            panelForInput.add(repsField);

                            int reps = Integer.parseInt(repsField.getText());

                            panelForInput.add(new JLabel("Weight:"));
                            panelForInput.add(weightField);

                            int weight = Integer.parseInt(weightField.getText());

                            workout.getExercises().get(i).getSets().get(j).setReps(reps);
                            workout.getExercises().get(i).getSets().get(j).setReps(weight);

                        }
                        if(i == workout.getExercises().size()){
                            // TODO Misto next se zobrazi tlacitko Finish
                        }
                        // TODO tady bude tlacitko next exercise, ktery dokud se neklikne tak bude drzet ten for s tim i
                        // TODO mozna predelat tu logiku, aby se neotevirali furt nove framy






                    }
                }
            });

            workoutPanel.add(singleWorkoutPanel);
        }

        JScrollPane scrollPane = new JScrollPane(workoutPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            dispose();
        });
    }

}
