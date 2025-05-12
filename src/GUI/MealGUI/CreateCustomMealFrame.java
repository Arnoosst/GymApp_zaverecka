package GUI.MealGUI;

import Model.Meal;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class CreateCustomMealFrame extends JFrame{

    private User user;
    private UserManager userManager;
    private JTextField kcalTextField;
    private JTextField nameTextField;
    private JTextField proteinTextField;
    private JTextField carbsTextField;
    private JTextField fatsTextField;

    public CreateCustomMealFrame(User user, UserManager userManager) {
        this.user = user;

        setTitle("Create custom meal");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI(user, userManager);

    }

    public void initGUI(User user, UserManager userManager) {
        JPanel createCustomMelaPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        kcalTextField = new JTextField();
        nameTextField = new JTextField();
        proteinTextField = new JTextField();
        carbsTextField = new JTextField();
        fatsTextField = new JTextField();



        createCustomMelaPanel.add(new JLabel("Name: "));
        createCustomMelaPanel.add(nameTextField);

        createCustomMelaPanel.add(new JLabel("Kcal: "));
        createCustomMelaPanel.add(kcalTextField);

        createCustomMelaPanel.add(new JLabel("Protein: "));
        createCustomMelaPanel.add(proteinTextField);

        createCustomMelaPanel.add(new JLabel("Carbs: "));
        createCustomMelaPanel.add(carbsTextField);

        createCustomMelaPanel.add(new JLabel("Fats: "));
        createCustomMelaPanel.add(fatsTextField);

        JButton createCustomMealButton = new JButton("Create Custom Meal");
        JButton backButton = new JButton("Back");
        createCustomMelaPanel.add(backButton, BorderLayout.WEST);
        createCustomMelaPanel.add(createCustomMealButton, BorderLayout.EAST);


        add(createCustomMelaPanel, BorderLayout.CENTER);

        createCustomMealButton.addActionListener(ev -> {
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
                MealFrame mealFrame = new MealFrame(user, userManager);
                mealFrame.setVisible(true);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
            }
        });


        backButton.addActionListener(ev -> {
           MealFrame mealFrame = new MealFrame(user, userManager);
           mealFrame.setVisible(true);
           dispose();
        });



    }







}
