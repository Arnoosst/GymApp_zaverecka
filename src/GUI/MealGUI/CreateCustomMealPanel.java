package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class CreateCustomMealPanel extends JPanel {

    private JTextField kcalTextField;
    private JTextField nameTextField;
    private JTextField proteinTextField;
    private JTextField carbsTextField;
    private JTextField fatsTextField;

    public CreateCustomMealPanel(User user, UserManager userManager, CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        nameTextField = new JTextField();
        kcalTextField = new JTextField();
        proteinTextField = new JTextField();
        carbsTextField = new JTextField();
        fatsTextField = new JTextField();

        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(nameTextField);

        inputPanel.add(new JLabel("Kcal: "));
        inputPanel.add(kcalTextField);

        inputPanel.add(new JLabel("Protein: "));
        inputPanel.add(proteinTextField);

        inputPanel.add(new JLabel("Carbs: "));
        inputPanel.add(carbsTextField);

        inputPanel.add(new JLabel("Fats: "));
        inputPanel.add(fatsTextField);

        JButton backButton = new JButton("Back");
        JButton createButton = new JButton("Create Custom Meal");

        inputPanel.add(backButton);
        inputPanel.add(createButton);

        add(inputPanel, BorderLayout.CENTER);

        createButton.addActionListener(ev -> {
            try {
                String name = nameTextField.getText();
                int kcal = Integer.parseInt(kcalTextField.getText());
                int protein = Integer.parseInt(proteinTextField.getText());
                int carbs = Integer.parseInt(carbsTextField.getText());
                int fats = Integer.parseInt(fatsTextField.getText());

                Meal customMeal = new Meal(name, kcal, protein, fats, carbs);
                user.addCustomMeal(customMeal);
                userManager.saveUsers();

                JOptionPane.showMessageDialog(this, "Meal added successfully!");
                cardLayout.show(parentPanel, "meal");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
            }
        });

        backButton.addActionListener(ev -> {
            cardLayout.show(parentPanel, "meal");
        });
    }
}
