package GUI.UserGUI;

import Model.Meal;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewMealLogsPanel extends JPanel{
    private User user;
    private CardLayout cardLayout;
    private JPanel parentPanel;



    public ViewMealLogsPanel(User user, CardLayout cardLayout, JPanel parentPanel) {
        this.user = user;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        setLayout(new BorderLayout());
        initGUI();
    }

    public void initGUI(){
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        HashMap<LocalDate, ArrayList<Meal>> meals = user.getMealLogs();
        if (meals != null && !meals.isEmpty()) {
            for (ArrayList<Meal> mealList : meals.values()) {
                for (Meal meal : mealList) {
                    textArea.append(meal.toString() + "\n\n");
                }
            }
        } else {
            textArea.setText("No meals.");
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev -> {
            cardLayout.show(parentPanel, "user");
        });

        add(new JScrollPane(textArea));
        add(closeButton, BorderLayout.SOUTH);

    }

    public void refresh() {
        removeAll();
        initGUI();
        revalidate();
        repaint();
    }

}
