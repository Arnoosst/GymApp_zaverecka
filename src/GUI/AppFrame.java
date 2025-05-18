package GUI;

import GUI.MealGUI.*;
import GUI.UserGUI.ChangeUserDataPanel;
import GUI.UserGUI.UserPanel;
import GUI.UserGUI.ViewMealLogsPanel;
import GUI.UserGUI.ViewWorkoutLogsPanel;
import GUI.WorkoutGUI.*;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private final CardLayout layout;
    private final JPanel cards;
    private final UserManager userManager;

    private User user;


    private ViewCustomWorkoutPanel viewCustomWorkoutPanel;
    private SelectUserWorkoutPanel selectUserWorkoutPanel;
    private DeleteCustomWorkoutPanel deleteCustomWorkoutPanel;
    private UserPanel userPanel;
    private WorkoutPanel workoutPanel;
    private ViewCustomMealPanel viewCustomMealPanel;
    private DeleteCustomMealPanel deleteCustomMealPanel;
    private CaloriesChartMenuPanel caloriesChartMenuPanel;
    private EditMealsPanel editMealsPanel;
    private ViewMealLogsPanel viewMealLogsPanel;
    private ViewWorkoutLogsPanel viewWorkoutLogsPanel;

    public AppFrame(UserManager userManager) {
        this.userManager = userManager;
        this.layout = new CardLayout();
        this.cards = new JPanel(layout);

        setTitle("Fitness App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        cards.add(new LoginPanel(userManager, layout, cards, this), "login");
        cards.add(new RegisterPanel(userManager, layout, cards), "register");

        add(cards);
        layout.show(cards, "login");

        setVisible(true);
    }


    public void initializeUserPanels(User user) {
        this.user = user;

        viewCustomWorkoutPanel = new ViewCustomWorkoutPanel(user, layout, cards);
        viewWorkoutLogsPanel = new ViewWorkoutLogsPanel(user, layout,cards);
        viewMealLogsPanel = new ViewMealLogsPanel(user, layout,cards);
        deleteCustomWorkoutPanel = new DeleteCustomWorkoutPanel(user, userManager, cards, layout);
        workoutPanel = new WorkoutPanel(user, cards, layout, viewCustomWorkoutPanel, deleteCustomWorkoutPanel);

        userPanel = new UserPanel(user, userManager, layout, cards, viewMealLogsPanel, viewWorkoutLogsPanel);
        selectUserWorkoutPanel = new SelectUserWorkoutPanel(user, userManager, cards, layout, workoutPanel);

        viewCustomMealPanel = new ViewCustomMealPanel(user, layout, cards);
        deleteCustomMealPanel = new DeleteCustomMealPanel(user, userManager, layout, cards);
        editMealsPanel = new EditMealsPanel(user, userManager, layout, cards);
        caloriesChartMenuPanel = new CaloriesChartMenuPanel(user, userManager, layout, cards, editMealsPanel);


        cards.add(new MainMenuPanel(user, userManager, layout, cards), "mainMenu");
        cards.add(new ViewPresetWorkoutsPanel(layout, cards), "viewPresetWorkouts");
        cards.add(viewCustomWorkoutPanel, "viewCustomWorkouts");
        cards.add(deleteCustomWorkoutPanel, "deleteWorkout");
        cards.add(selectUserWorkoutPanel, "selectUserWorkout");
        cards.add(workoutPanel, "workout");
        cards.add(new StartWorkoutPanel(user, layout, cards, selectUserWorkoutPanel), "startWorkout");
        cards.add(new SelectPreLoadWorkoutsPanel(user, userManager, cards, layout, workoutPanel), "selectPreLoadWorkout");
        cards.add(new CreateWorkoutPanel(user, userManager, cards, layout), "createWorkout");
        cards.add(viewWorkoutLogsPanel, "viewWorkoutLogs");
        cards.add(viewMealLogsPanel, "viewMealLogs");
        cards.add(userPanel, "user");
        cards.add(new ChangeUserDataPanel(user, userManager, layout, cards, userPanel), "changeUserData");

        cards.add(new ViewPresetMealPanel(layout, cards), "viewPresetMeal");
        cards.add(viewCustomMealPanel, "viewCustomMeal");
        cards.add(new MealPanel(user, userManager, layout, cards, viewCustomMealPanel, deleteCustomMealPanel), "meal");
        cards.add(new ManageMealsPanel(user, userManager, layout, cards, caloriesChartMenuPanel), "manageMeals");
        cards.add(editMealsPanel, "editMeals");
        cards.add(deleteCustomMealPanel, "deleteCustomMeal");
        cards.add(new CreateCustomMealPanel(user, userManager, layout, cards), "createCustomMeal");
        cards.add(caloriesChartMenuPanel, "caloriesChartMenu");
        cards.add(new AddMealPanel(user, userManager, layout, cards), "addMeal");
        cards.add(new AddMealFromOwnPanel(user, userManager, layout, cards), "addMealFromOwn");
        cards.add(new AddMealFromPreLoadPanel(user, userManager, layout, cards), "addMealFromPreLoad");
    }
}
