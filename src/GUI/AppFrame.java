package GUI;

import GUI.MealGUI.*;
import GUI.UserGUI.ChangeUserDataPanel;
import GUI.UserGUI.UserPanel;
import GUI.UserGUI.ViewMealLogsPanel;
import GUI.UserGUI.ViewWorkoutLogsPanel;
import GUI.WorkoutGUI.*;
import Model.Meal;
import Model.User;
import Model.UserManager;
import Model.Workout;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    public AppFrame(UserManager userManager, User user) {
        setTitle("Fitness App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CardLayout layout = new CardLayout();
        JPanel cards = new JPanel(layout);


        cards.add(new LoginPanel(userManager, layout, cards), "login");
        cards.add(new RegisterPanel(userManager, layout, cards), "register");
        cards.add(new MainMenuPanel(user, userManager, layout, cards), "mainMenu");


        cards.add(new WorkoutPanel(user, cards, layout), "workout");
        cards.add(new ViewPresetWorkoutsPanel(layout, cards), "viewPresetWorkouts");
        cards.add(new ViewCustomWorkoutPanel(user, layout, cards), "viewCustomWorkouts");
        cards.add(new StartWorkoutPanel(user, layout, cards), "startWorkout");
        cards.add(new SelectUserWorkoutPanel(user, userManager, cards, layout), "selectUserWorkout");
        cards.add(new SelectPreLoadWorkoutsPanel(user, userManager, cards, layout), "selectPreLoadWorkout");
        cards.add(new DeleteCustomWorkoutPanel(user, userManager, cards, layout), "deleteWorkout");
        cards.add(new CreateWorkoutPanel(user, userManager, cards, layout), "createWorkout");

        cards.add(new ViewWorkoutLogsPanel(user, layout, cards), "viewWorkoutLogs");
        cards.add(new ViewMealLogsPanel(user, layout, cards), "viewMealLogs");
        cards.add(new UserPanel(user, userManager, layout, cards), "user");
        cards.add(new ChangeUserDataPanel(user, userManager, layout, cards), "changeUserData");



        cards.add(new ViewPresetMealPanel(layout, cards), "viewPresetMeal");
        cards.add(new ViewCustomMealPanel(user, layout, cards), "viewCustomMeal");
        cards.add(new MealPanel(user, userManager, layout, cards), "meal");
        cards.add(new ManageMealsPanel(user, userManager, layout, cards), "manageMeals");
        cards.add(new EditMealsPanel(user, userManager, layout, cards), "editMeals");
        cards.add(new DeleteCustomMealPanel(user, userManager, layout, cards), "deleteCustomMeal");
        cards.add(new CreateCustomMealPanel(user, userManager, layout, cards), "createCustomMeal");
        cards.add(new CaloriesChartMenuPanel(user, userManager, layout, cards), "caloriesChartMenu");
        cards.add(new AddMealPanel(user, userManager, layout, cards), "addMeal");
        cards.add(new AddMealFromOwnPanel(user, userManager, layout, cards), "addMealFromOwn");
        cards.add(new AddMealFromPreLoadPanel(user, userManager, layout, cards), "addMealFromPreLoad");


        add(cards);
        layout.show(cards, "login");

        setVisible(true);
    }
}
