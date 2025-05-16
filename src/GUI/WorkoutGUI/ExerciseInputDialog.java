package GUI.WorkoutGUI;

import Model.Exercise;
import Model.ExerciseSets;

import javax.swing.*;
import java.awt.*;

public class ExerciseInputDialog extends JDialog {

    private JTextField nameField;
    private JTextField repsField;
    private JTextField setsField;
    private JTextField weightField;
    private Exercise result;

    public ExerciseInputDialog(JFrame parent) {
        super(parent, "Add Exercise", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        initGUI();
    }

    private void initGUI() {
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 5));

        nameField = new JTextField();
        setsField = new JTextField();
        repsField = new JTextField();
        weightField = new JTextField();

        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Number of Sets:"));
        form.add(setsField);
        form.add(new JLabel("Reps per Set:"));
        form.add(repsField);
        form.add(new JLabel("Weight (kg):"));
        form.add(weightField);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            try {
                int sets = Integer.parseInt(setsField.getText().trim());
                int reps = Integer.parseInt(repsField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());

                if (name.isEmpty() || sets <= 0 || reps <= 0 || weight < 0) {
                    throw new IllegalArgumentException();
                }

                Exercise exercise = new Exercise(name);
                exercise.initializeSets(sets);
                for (ExerciseSets s : exercise.getSets()) {
                    s.setReps(reps);
                    s.setWeight(weight);
                }
                result = exercise;
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            result = null;
            dispose();
        });

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(saveButton);
        buttons.add(cancelButton);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    public static Exercise showDialog(JFrame parent) {
        ExerciseInputDialog dialog = new ExerciseInputDialog(parent);
        dialog.setVisible(true);
        return dialog.result;
    }
}